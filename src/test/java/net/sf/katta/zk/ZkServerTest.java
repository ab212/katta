/**
 * Copyright 2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sf.katta.zk;

import net.sf.katta.AbstractKattaTest;

import com.yahoo.zookeeper.Watcher;
import com.yahoo.zookeeper.ZooKeeper;
import com.yahoo.zookeeper.ZooDefs.Ids;
import com.yahoo.zookeeper.ZooKeeper.States;
import com.yahoo.zookeeper.proto.WatcherEvent;

public class ZkServerTest extends AbstractKattaTest implements Watcher {

  public void testServer() throws Exception {
    stopZkServer();
    final String path = "/testPath";
    ZooKeeper zk = null;
    try {
      zk = new ZooKeeper(_conf.getZKServers(), _conf.getZKClientPort(), this);
      zk.create(path, null, Ids.OPEN_ACL_UNSAFE, 0);
      fail("no server yet started");
    } catch (final Exception e) {
      zk.close();
    }

    startZkServer();
    zk = new ZooKeeper(_conf.getZKServers(), _conf.getZKClientPort(), this);
    while (zk.getState() == States.CONNECTING) {
      Thread.sleep(500);
    }

    zk.create(path, new byte[0], Ids.OPEN_ACL_UNSAFE, 0);

    zk.getChildren(path, true);
    zk.create(path + "/2", new byte[0], Ids.OPEN_ACL_UNSAFE, 0);
    zk.getChildren(path, true);
    zk.create(path + "/3", new byte[0], Ids.OPEN_ACL_UNSAFE, 0);
    zk.close();
  }

  public void process(final WatcherEvent event) {
    // System.out.println("path: " + event.getPath());
    // System.out.println("type: " + event.getType());
    // System.out.println("state: " + event.getState());
  }

}
