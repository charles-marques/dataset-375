/*
 * This file is provided to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.basho.riak.hadoop;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.InputSplit;

import com.basho.riak.hadoop.config.RiakLocation;

/**
 * Riak specific extension of {@link InputSplit}
 * 
 * @author russell
 * 
 */
public class RiakInputSplit extends InputSplit implements Writable {

    private BucketKey[] inputs;
    private RiakLocation location;

    public RiakInputSplit() {};

    public RiakInputSplit(List<BucketKey> split, RiakLocation location) {
        this.inputs = split.toArray(new BucketKey[split.size()]);
        this.location = location;
    }

    /**
     * @return the location for the split (this is where the record reader for
     *         this split will load data from)
     */
    public synchronized RiakLocation getLocation() {
        return location;
    }

    /**
     * @return the inputs the collection of keys whose data will be fetched by
     *         the record reader
     */
    public synchronized Collection<BucketKey> getInputs() {
        return Arrays.asList(inputs.clone());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.hadoop.mapreduce.InputSplit#getLength()
     */
    @Override public long getLength() throws IOException, InterruptedException {
        return inputs.length;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.hadoop.mapreduce.InputSplit#getLocations()
     */
    @Override public String[] getLocations() throws IOException, InterruptedException {
        return new String[] { location.asString() };
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.hadoop.io.Writable#readFields(java.io.DataInput)
     */
    public void readFields(DataInput din) throws IOException {
        location = RiakLocation.fromString(din.readUTF());
        inputs = new BucketKey[din.readInt()];

        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = new BucketKey(din.readUTF(), din.readUTF());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.hadoop.io.Writable#write(java.io.DataOutput)
     */
    public void write(DataOutput dout) throws IOException {
        dout.writeUTF(location.asString());
        dout.writeInt(inputs.length);

        for (BucketKey bk : inputs) {
            dout.writeUTF(bk.getBucket());
            dout.writeUTF(bk.getKey());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(inputs);
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof RiakInputSplit)) {
            return false;
        }
        RiakInputSplit other = (RiakInputSplit) obj;
        if (!Arrays.equals(inputs, other.inputs)) {
            return false;
        }
        if (location == null) {
            if (other.location != null) {
                return false;
            }
        } else if (!location.equals(other.location)) {
            return false;
        }
        return true;
    }

}
