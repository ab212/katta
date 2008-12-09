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
package net.sf.katta.master;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.sf.katta.util.DefaultDateFormat;

import org.apache.hadoop.io.Writable;

public class MasterMetaData implements Writable {

  private String _masterName;
  private long _startTime;

  public MasterMetaData() {
    // needed for serialization
  }

  public MasterMetaData(final String masterName, final long startTime) {
    _masterName = masterName;
    _startTime = startTime;
  }

  public void readFields(final DataInput in) throws IOException {
    _masterName = in.readUTF();
    _startTime = in.readLong();
  }

  public void write(final DataOutput out) throws IOException {
    out.writeUTF(_masterName);
    out.writeLong(_startTime);
  }

  public String getStartTimeAsString() {
    return DefaultDateFormat.longToDateString(_startTime);
  }

  public String getMasterName() {
    return _masterName;
  }

  public long getStartTime() {
    return _startTime;
  }

}
