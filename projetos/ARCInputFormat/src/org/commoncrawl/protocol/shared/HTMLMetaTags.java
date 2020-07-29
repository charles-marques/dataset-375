// File generated by rpc compiler. Do not edit.

package org.commoncrawl.protocol.shared;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.BitSet;
import java.io.IOException;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.record.Buffer;
import org.commoncrawl.util.shared.FlexBuffer;
import org.commoncrawl.util.shared.TextBytes;
import org.commoncrawl.util.shared.MurmurHash;
import org.commoncrawl.util.shared.ImmutableBuffer;
import org.commoncrawl.rpc.BinaryProtocol;
import org.apache.hadoop.util.ReflectionUtils;
import org.apache.hadoop.conf.Configuration;
// Generated File: HTMLMetaTags
public class HTMLMetaTags extends org.commoncrawl.rpc.RPCStruct<HTMLMetaTags>  implements Writable{
  
  // optimized constructor helper 
  public static HTMLMetaTags newInstance(Configuration conf) {
      return ReflectionUtils.newInstance(HTMLMetaTags.class,conf);
  }
  // Writable Implementation
  public void write(DataOutput out) throws IOException{ 
    this.serialize(out,new BinaryProtocol());
  }
  
  public void readFields(DataInput  in) throws IOException{ 
    this.deserialize(in,new BinaryProtocol());
  }
  
  
  // Field Constants
  public static final int Field_ROBOTSFLAGS = 1;
  public static final int Field_PRAGMAS = 2;
  public static final int Field_OPTIONALREFRESHURL = 3;
  public static final int Field_OPTIONALREFRESHTIME = 4;
  public static final int Field_OTHERMETATAGS = 6;
  static final int FieldID_MAX=Field_OTHERMETATAGS;
  
  // Enumerations
  
  // Enum:RobotsFlags
  public static final class RobotsFlags {
    public static final int ALL = 1;
    public static final int NO_INDEX = 2;
    public static final int NO_FOLLOW = 4;
    public static final int NO_ARCHIVE = 8;
    
    public static String toString(int enumValue){
      switch (enumValue) {
        case 1: return "ALL";
        case 2: return "NO_INDEX";
        case 4: return "NO_FOLLOW";
        case 8: return "NO_ARCHIVE";
        default: return "";
      }
    }
  }
  // Enum:Pragmas
  public static final class Pragmas {
    public static final int NO_CACHE = 1;
    
    public static String toString(int enumValue){
      switch (enumValue) {
        case 1: return "NO_CACHE";
        default: return "";
      }
    }
  }
  // Field Declarations
  private BitSet __validFields = new BitSet(FieldID_MAX+1);
  
  private byte robotsFlags;
  private byte pragmas;
  private TextBytes optionalRefreshURL=  new TextBytes();
  private int optionalRefreshTime;
  private TextBytes otherMetaTags=  new TextBytes();
  
  // Default Constructor
  public HTMLMetaTags() { }
  
  // Accessors
  
  public final boolean isFieldDirty(int fieldId) { return __validFields.get(fieldId); }
  public final HTMLMetaTags  setFieldDirty(int fieldId) { __validFields.set(fieldId); return this; }
  
  public final HTMLMetaTags  setFieldClean(int fieldId) { __validFields.clear(fieldId); return this; }
  
  public byte getRobotsFlags() {
    return robotsFlags;
  }
  public HTMLMetaTags setRobotsFlags( byte robotsFlags) {
    __validFields.set(Field_ROBOTSFLAGS);
    this.robotsFlags=robotsFlags;
    return this;
  }
  public byte getPragmas() {
    return pragmas;
  }
  public HTMLMetaTags setPragmas( byte pragmas) {
    __validFields.set(Field_PRAGMAS);
    this.pragmas=pragmas;
    return this;
  }
  public TextBytes getOptionalRefreshURLAsTextBytes() {
    return optionalRefreshURL;
  }
  public String getOptionalRefreshURL() {
    return optionalRefreshURL.toString();
  }
  public HTMLMetaTags  setOptionalRefreshURL( String optionalRefreshURL) {
    __validFields.set(Field_OPTIONALREFRESHURL);
    this.optionalRefreshURL.set(optionalRefreshURL);
    return this;
  }
  public int getOptionalRefreshTime() {
    return optionalRefreshTime;
  }
  public HTMLMetaTags setOptionalRefreshTime( int optionalRefreshTime) {
    __validFields.set(Field_OPTIONALREFRESHTIME);
    this.optionalRefreshTime=optionalRefreshTime;
    return this;
  }
  public TextBytes getOtherMetaTagsAsTextBytes() {
    return otherMetaTags;
  }
  public String getOtherMetaTags() {
    return otherMetaTags.toString();
  }
  public HTMLMetaTags  setOtherMetaTags( String otherMetaTags) {
    __validFields.set(Field_OTHERMETATAGS);
    this.otherMetaTags.set(otherMetaTags);
    return this;
  }
  // Object Dirty support 
  
  public final boolean isObjectDirty(){
    boolean isDirty = !__validFields.isEmpty();
    return isDirty;
  }
  
  // serialize implementation 
  public final void serialize(DataOutput output,BinaryProtocol encoder)
  throws java.io.IOException {
    encoder.beginFields(output);
    // serialize field:robotsFlags
    if (__validFields.get(Field_ROBOTSFLAGS)){
      encoder.beginField(output,"robotsFlags",Field_ROBOTSFLAGS);
      encoder.writeByte(output,robotsFlags);
    }
    // serialize field:pragmas
    if (__validFields.get(Field_PRAGMAS)){
      encoder.beginField(output,"pragmas",Field_PRAGMAS);
      encoder.writeByte(output,pragmas);
    }
    // serialize field:optionalRefreshURL
    if (__validFields.get(Field_OPTIONALREFRESHURL)){
      encoder.beginField(output,"optionalRefreshURL",Field_OPTIONALREFRESHURL);
      encoder.writeTextBytes(output,optionalRefreshURL);
    }
    // serialize field:optionalRefreshTime
    if (__validFields.get(Field_OPTIONALREFRESHTIME)){
      encoder.beginField(output,"optionalRefreshTime",Field_OPTIONALREFRESHTIME);
      encoder.writeVInt(output,optionalRefreshTime);
    }
    // serialize field:otherMetaTags
    if (__validFields.get(Field_OTHERMETATAGS)){
      encoder.beginField(output,"otherMetaTags",Field_OTHERMETATAGS);
      encoder.writeTextBytes(output,otherMetaTags);
    }
    encoder.endFields(output);
  }
  // deserialize implementation 
  public final void deserialize(DataInput input, BinaryProtocol decoder)
  throws java.io.IOException {
    // clear existing data first  
    clear();
    
    // reset protocol object to unknown field id enconding mode (for compatibility)
    decoder.pushFieldIdEncodingMode(BinaryProtocol.FIELD_ID_ENCODING_MODE_UNKNOWN);
    // keep reading fields until terminator (-1) is located 
    int fieldId;
    while ((fieldId = decoder.readFieldId(input)) != -1) { 
      switch (fieldId) { 
        case Field_ROBOTSFLAGS:{
          __validFields.set(Field_ROBOTSFLAGS);
          robotsFlags=decoder.readByte(input);
        }
        break;
        case Field_PRAGMAS:{
          __validFields.set(Field_PRAGMAS);
          pragmas=decoder.readByte(input);
        }
        break;
        case Field_OPTIONALREFRESHURL:{
          __validFields.set(Field_OPTIONALREFRESHURL);
          decoder.readTextBytes(input,optionalRefreshURL);
        }
        break;
        case Field_OPTIONALREFRESHTIME:{
          __validFields.set(Field_OPTIONALREFRESHTIME);
          optionalRefreshTime=decoder.readVInt(input);
        }
        break;
        case Field_OTHERMETATAGS:{
          __validFields.set(Field_OTHERMETATAGS);
          decoder.readTextBytes(input,otherMetaTags);
        }
        break;
      }
    }
    // pop extra encoding mode off of stack 
    decoder.popFieldIdEncodingMode();
  }
  // clear implementation 
  public final void clear() {
    __validFields.clear();
    robotsFlags=0;
    pragmas=0;
    optionalRefreshURL.clear();
    optionalRefreshTime=0;
    otherMetaTags.clear();
  }
  // equals implementation 
  public final boolean equals(final Object peer_) {
    if (!(peer_ instanceof HTMLMetaTags)) {
      return false;
    }
    if (peer_ == this) {
      return true;
    }
    HTMLMetaTags peer = (HTMLMetaTags) peer_;
    boolean ret = __validFields.equals(peer.__validFields);
    if (!ret) return ret;
    if (__validFields.get(Field_ROBOTSFLAGS)) {
      ret = (robotsFlags==peer.robotsFlags);
      if (!ret) return ret;
    }
    if (__validFields.get(Field_PRAGMAS)) {
      ret = (pragmas==peer.pragmas);
      if (!ret) return ret;
    }
    if (__validFields.get(Field_OPTIONALREFRESHURL)) {
      ret = optionalRefreshURL.equals(peer.optionalRefreshURL);
      if (!ret) return ret;
    }
    if (__validFields.get(Field_OPTIONALREFRESHTIME)) {
      ret = (optionalRefreshTime==peer.optionalRefreshTime);
      if (!ret) return ret;
    }
    if (__validFields.get(Field_OTHERMETATAGS)) {
      ret = otherMetaTags.equals(peer.otherMetaTags);
      if (!ret) return ret;
    }
    return ret;
  }
  // clone implementation 
  @SuppressWarnings("unchecked")
  public final Object clone() throws CloneNotSupportedException {
    HTMLMetaTags other = new HTMLMetaTags();
    other.__validFields.or(this.__validFields);
    if (__validFields.get(Field_ROBOTSFLAGS)){
      other.robotsFlags= this.robotsFlags;
    }
    if (__validFields.get(Field_PRAGMAS)){
      other.pragmas= this.pragmas;
    }
    if (__validFields.get(Field_OPTIONALREFRESHURL)){
      other.optionalRefreshURL= (TextBytes)this.optionalRefreshURL.clone();
    }
    if (__validFields.get(Field_OPTIONALREFRESHTIME)){
      other.optionalRefreshTime= this.optionalRefreshTime;
    }
    if (__validFields.get(Field_OTHERMETATAGS)){
      other.otherMetaTags= (TextBytes)this.otherMetaTags.clone();
    }
    return other;
  }
  // merge implementation 
  @SuppressWarnings("unchecked")
  public final void merge(HTMLMetaTags peer) throws CloneNotSupportedException  {
    __validFields.or(peer.__validFields);
    if (peer.__validFields.get(Field_ROBOTSFLAGS)){
      this.robotsFlags= peer.robotsFlags;
    }
    if (peer.__validFields.get(Field_PRAGMAS)){
      this.pragmas= peer.pragmas;
    }
    if (peer.__validFields.get(Field_OPTIONALREFRESHURL)){
      this.optionalRefreshURL= (TextBytes)peer.optionalRefreshURL.clone();
    }
    if (peer.__validFields.get(Field_OPTIONALREFRESHTIME)){
      this.optionalRefreshTime= peer.optionalRefreshTime;
    }
    if (peer.__validFields.get(Field_OTHERMETATAGS)){
      this.otherMetaTags= (TextBytes)peer.otherMetaTags.clone();
    }
  }
  // hashCode implementation 
  public final int hashCode() {
    int result = 1;
    result = MurmurHash.hashInt((int)robotsFlags,result);
    result = MurmurHash.hashInt((int)pragmas,result);
    result = MurmurHash.hash(optionalRefreshURL.getBytes(),optionalRefreshURL.getOffset(),optionalRefreshURL.getLength(),result);
    result = MurmurHash.hashInt((int)optionalRefreshTime,result);
    result = MurmurHash.hash(otherMetaTags.getBytes(),otherMetaTags.getOffset(),otherMetaTags.getLength(),result);
    return result;
  }
}
