package org.example;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ZookeeperCreate {
    private ZooKeeper zk;

    private int numberOfWorkers = 10;

    // Method to create znode in zookeeper ensemble
    public void createParent(String path, byte[] data) throws KeeperException,InterruptedException {
        zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public Stat ZnodeDoesExist(String path) throws KeeperException,InterruptedException {
        return zk.exists(path, false);
    }

    private int getWaitingTime() {
        Random r = new Random();
        int low = 10, high = 3000;
        int waitingTimeInMilliSeconds = r.nextInt(high - low) + low;
        return waitingTimeInMilliSeconds;
    }
    public void parent() throws IOException {
        PrintWriter log = new PrintWriter("logfile", StandardCharsets.UTF_8);
        String parentNodePath = "/nodes";
        String childNodePath = "/nodes/node";

        // data in byte array
        byte[] parentData = "parent node dummy data".getBytes();

        try {
            ZookeeperConnection conn = new ZookeeperConnection();
            zk = conn.connect("localhost");
            if(ZnodeDoesExist(parentNodePath)!=null) {
                System.out.println("The parent node does exist");
                for(String childPath: zk.getChildren(parentNodePath, false)) {
                    System.out.println("Trying to delete: "+childPath);
                    zk.delete(parentNodePath+"/"+childPath, -1);
                }
                zk.delete(parentNodePath, -1);
            }
            createParent(parentNodePath, parentData);

            Worker[] workers = new Worker[numberOfWorkers];

            for(int i=0;i<numberOfWorkers;i++) {
                workers[i] = new Worker(parentNodePath, zk, log);
                workers[i].createWorker(childNodePath);

                if(i>0) {
                    workers[i].setNodeToBeWatchedAndSetWatch(workers[i-1].getWorkerPath());
                } else {
                    log.println("---------------------------------------------------------");
                    log.println("First worker: "+workers[i].getWorkerPath()+" has been designated as the master");
                    log.println("---------------------------------------------------------");
                }
            }
            log.println("\n");

            ArrayList<Integer> orderOfKillingWorkers = new ArrayList<>();
            for(int i=0;i<numberOfWorkers;i++) {
                orderOfKillingWorkers.add(i);
            }
            Collections.shuffle(orderOfKillingWorkers);

            for(int workerIndex: orderOfKillingWorkers) {
                Thread.sleep(getWaitingTime());
                workers[workerIndex].killWorkerNow();
            }

            System.out.println("EXITING ZookeeperCreate");
            log.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage()); //Catch error message
        }
    }
}