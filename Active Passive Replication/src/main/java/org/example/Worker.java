package org.example;

import org.apache.zookeeper.*;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

public class Worker {
    private PrintWriter log;
    private String workerPath;
    private String nodeBeingWatched;
    private String parentPath;
    private ZooKeeper zk;
    private byte[] childData = "child node dummy data".getBytes();

    public Worker(String parentPath, ZooKeeper zk, PrintWriter log) {
        this.parentPath = parentPath;
        this.zk = zk;
        this.log = log;
    }

    private void getNextSmallestNodeAndSetWatch() throws InterruptedException, KeeperException {
        List<String> childNodes = zk.getChildren(parentPath, false);
        Collections.sort(childNodes);
        for(int i=0;i<childNodes.size();i++) {
            String childNodePath = parentPath + "/" + childNodes.get(i);
            if(childNodePath.equals(workerPath)) {
                if(i>0) {
                    String nodeToBeWatched = parentPath + "/" + childNodes.get(i-1);
                    setNodeToBeWatchedAndSetWatch(nodeToBeWatched);
                } else {
                    log.println("---------------------------------------------------------");
                    log.println("Worker:"+workerPath+" has assumed the role of master");
                    log.println("---------------------------------------------------------");
                }
            }
        }
    }
    private Watcher watcher = new Watcher() {
        @Override
        public void process(WatchedEvent event) {
            if(event.getType() == Event.EventType.NodeDeleted) {
                log.println(workerPath+" was watching: "+event.getPath()+" and that node got killed");
                try {
                    getNextSmallestNodeAndSetWatch();
                } catch (InterruptedException | KeeperException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    };

    public String getWorkerPath() {
        return workerPath;
    }

    public void createWorker(String genericNodePath) throws InterruptedException, KeeperException {
        this.workerPath = zk.create(genericNodePath, childData, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        log.println("Worker created with path:" + this.workerPath);
    }

    public void setNodeToBeWatchedAndSetWatch(String nodeToBeWatched) throws InterruptedException, KeeperException {
        this.nodeBeingWatched = nodeToBeWatched;
        log.println("Now "+workerPath+" has set a watch on:"+nodeToBeWatched+"\n");
        zk.addWatch(nodeToBeWatched, watcher, AddWatchMode.PERSISTENT);
    }

    public void killWorkerNow() throws InterruptedException, KeeperException {
        if(this.nodeBeingWatched!=null) {
            zk.removeWatches(nodeBeingWatched, watcher, Watcher.WatcherType.Any, true);
        }
        zk.delete(workerPath, -1);
        log.println("Worker:"+workerPath+" has been killed");
    }
}
