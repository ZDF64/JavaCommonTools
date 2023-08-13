/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package zdf.learn.com.commonUtils.data.avro.schema.can300;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class CanInformationList extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 9035708728862011477L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"CanInformationList\",\"namespace\":\"zdf.learn.com.commonUtils.data.avro.schema.can300\",\"fields\":[{\"name\":\"canId\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"dataLengthAfterCompression\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"collectType\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"canType\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"time\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"outsideUseDataMap\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}],\"default\":null}],\"default\":null}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<CanInformationList> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<CanInformationList> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<CanInformationList> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<CanInformationList> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<CanInformationList> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this CanInformationList to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a CanInformationList from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a CanInformationList instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static CanInformationList fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.CharSequence canId;
  private java.lang.Long dataLengthAfterCompression;
  private java.lang.Long collectType;
  private java.lang.Long canType;
  private java.lang.CharSequence time;
  private java.util.Map<java.lang.CharSequence,java.util.Map<java.lang.CharSequence,java.lang.CharSequence>> outsideUseDataMap;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public CanInformationList() {}

  /**
   * All-args constructor.
   * @param canId The new value for canId
   * @param dataLengthAfterCompression The new value for dataLengthAfterCompression
   * @param collectType The new value for collectType
   * @param canType The new value for canType
   * @param time The new value for time
   * @param outsideUseDataMap The new value for outsideUseDataMap
   */
  public CanInformationList(java.lang.CharSequence canId, java.lang.Long dataLengthAfterCompression, java.lang.Long collectType, java.lang.Long canType, java.lang.CharSequence time, java.util.Map<java.lang.CharSequence,java.util.Map<java.lang.CharSequence,java.lang.CharSequence>> outsideUseDataMap) {
    this.canId = canId;
    this.dataLengthAfterCompression = dataLengthAfterCompression;
    this.collectType = collectType;
    this.canType = canType;
    this.time = time;
    this.outsideUseDataMap = outsideUseDataMap;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return canId;
    case 1: return dataLengthAfterCompression;
    case 2: return collectType;
    case 3: return canType;
    case 4: return time;
    case 5: return outsideUseDataMap;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: canId = (java.lang.CharSequence)value$; break;
    case 1: dataLengthAfterCompression = (java.lang.Long)value$; break;
    case 2: collectType = (java.lang.Long)value$; break;
    case 3: canType = (java.lang.Long)value$; break;
    case 4: time = (java.lang.CharSequence)value$; break;
    case 5: outsideUseDataMap = (java.util.Map<java.lang.CharSequence,java.util.Map<java.lang.CharSequence,java.lang.CharSequence>>)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'canId' field.
   * @return The value of the 'canId' field.
   */
  public java.lang.CharSequence getCanId() {
    return canId;
  }


  /**
   * Sets the value of the 'canId' field.
   * @param value the value to set.
   */
  public void setCanId(java.lang.CharSequence value) {
    this.canId = value;
  }

  /**
   * Gets the value of the 'dataLengthAfterCompression' field.
   * @return The value of the 'dataLengthAfterCompression' field.
   */
  public java.lang.Long getDataLengthAfterCompression() {
    return dataLengthAfterCompression;
  }


  /**
   * Sets the value of the 'dataLengthAfterCompression' field.
   * @param value the value to set.
   */
  public void setDataLengthAfterCompression(java.lang.Long value) {
    this.dataLengthAfterCompression = value;
  }

  /**
   * Gets the value of the 'collectType' field.
   * @return The value of the 'collectType' field.
   */
  public java.lang.Long getCollectType() {
    return collectType;
  }


  /**
   * Sets the value of the 'collectType' field.
   * @param value the value to set.
   */
  public void setCollectType(java.lang.Long value) {
    this.collectType = value;
  }

  /**
   * Gets the value of the 'canType' field.
   * @return The value of the 'canType' field.
   */
  public java.lang.Long getCanType() {
    return canType;
  }


  /**
   * Sets the value of the 'canType' field.
   * @param value the value to set.
   */
  public void setCanType(java.lang.Long value) {
    this.canType = value;
  }

  /**
   * Gets the value of the 'time' field.
   * @return The value of the 'time' field.
   */
  public java.lang.CharSequence getTime() {
    return time;
  }


  /**
   * Sets the value of the 'time' field.
   * @param value the value to set.
   */
  public void setTime(java.lang.CharSequence value) {
    this.time = value;
  }

  /**
   * Gets the value of the 'outsideUseDataMap' field.
   * @return The value of the 'outsideUseDataMap' field.
   */
  public java.util.Map<java.lang.CharSequence,java.util.Map<java.lang.CharSequence,java.lang.CharSequence>> getOutsideUseDataMap() {
    return outsideUseDataMap;
  }


  /**
   * Sets the value of the 'outsideUseDataMap' field.
   * @param value the value to set.
   */
  public void setOutsideUseDataMap(java.util.Map<java.lang.CharSequence,java.util.Map<java.lang.CharSequence,java.lang.CharSequence>> value) {
    this.outsideUseDataMap = value;
  }

  /**
   * Creates a new CanInformationList RecordBuilder.
   * @return A new CanInformationList RecordBuilder
   */
  public static zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList.Builder newBuilder() {
    return new zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList.Builder();
  }

  /**
   * Creates a new CanInformationList RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new CanInformationList RecordBuilder
   */
  public static zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList.Builder newBuilder(zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList.Builder other) {
    if (other == null) {
      return new zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList.Builder();
    } else {
      return new zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList.Builder(other);
    }
  }

  /**
   * Creates a new CanInformationList RecordBuilder by copying an existing CanInformationList instance.
   * @param other The existing instance to copy.
   * @return A new CanInformationList RecordBuilder
   */
  public static zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList.Builder newBuilder(zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList other) {
    if (other == null) {
      return new zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList.Builder();
    } else {
      return new zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList.Builder(other);
    }
  }

  /**
   * RecordBuilder for CanInformationList instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<CanInformationList>
    implements org.apache.avro.data.RecordBuilder<CanInformationList> {

    private java.lang.CharSequence canId;
    private java.lang.Long dataLengthAfterCompression;
    private java.lang.Long collectType;
    private java.lang.Long canType;
    private java.lang.CharSequence time;
    private java.util.Map<java.lang.CharSequence,java.util.Map<java.lang.CharSequence,java.lang.CharSequence>> outsideUseDataMap;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.canId)) {
        this.canId = data().deepCopy(fields()[0].schema(), other.canId);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.dataLengthAfterCompression)) {
        this.dataLengthAfterCompression = data().deepCopy(fields()[1].schema(), other.dataLengthAfterCompression);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.collectType)) {
        this.collectType = data().deepCopy(fields()[2].schema(), other.collectType);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.canType)) {
        this.canType = data().deepCopy(fields()[3].schema(), other.canType);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.time)) {
        this.time = data().deepCopy(fields()[4].schema(), other.time);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.outsideUseDataMap)) {
        this.outsideUseDataMap = data().deepCopy(fields()[5].schema(), other.outsideUseDataMap);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
    }

    /**
     * Creates a Builder by copying an existing CanInformationList instance
     * @param other The existing instance to copy.
     */
    private Builder(zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.canId)) {
        this.canId = data().deepCopy(fields()[0].schema(), other.canId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.dataLengthAfterCompression)) {
        this.dataLengthAfterCompression = data().deepCopy(fields()[1].schema(), other.dataLengthAfterCompression);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.collectType)) {
        this.collectType = data().deepCopy(fields()[2].schema(), other.collectType);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.canType)) {
        this.canType = data().deepCopy(fields()[3].schema(), other.canType);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.time)) {
        this.time = data().deepCopy(fields()[4].schema(), other.time);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.outsideUseDataMap)) {
        this.outsideUseDataMap = data().deepCopy(fields()[5].schema(), other.outsideUseDataMap);
        fieldSetFlags()[5] = true;
      }
    }

    /**
      * Gets the value of the 'canId' field.
      * @return The value.
      */
    public java.lang.CharSequence getCanId() {
      return canId;
    }


    /**
      * Sets the value of the 'canId' field.
      * @param value The value of 'canId'.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList.Builder setCanId(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.canId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'canId' field has been set.
      * @return True if the 'canId' field has been set, false otherwise.
      */
    public boolean hasCanId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'canId' field.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList.Builder clearCanId() {
      canId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'dataLengthAfterCompression' field.
      * @return The value.
      */
    public java.lang.Long getDataLengthAfterCompression() {
      return dataLengthAfterCompression;
    }


    /**
      * Sets the value of the 'dataLengthAfterCompression' field.
      * @param value The value of 'dataLengthAfterCompression'.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList.Builder setDataLengthAfterCompression(java.lang.Long value) {
      validate(fields()[1], value);
      this.dataLengthAfterCompression = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'dataLengthAfterCompression' field has been set.
      * @return True if the 'dataLengthAfterCompression' field has been set, false otherwise.
      */
    public boolean hasDataLengthAfterCompression() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'dataLengthAfterCompression' field.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList.Builder clearDataLengthAfterCompression() {
      dataLengthAfterCompression = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'collectType' field.
      * @return The value.
      */
    public java.lang.Long getCollectType() {
      return collectType;
    }


    /**
      * Sets the value of the 'collectType' field.
      * @param value The value of 'collectType'.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList.Builder setCollectType(java.lang.Long value) {
      validate(fields()[2], value);
      this.collectType = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'collectType' field has been set.
      * @return True if the 'collectType' field has been set, false otherwise.
      */
    public boolean hasCollectType() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'collectType' field.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList.Builder clearCollectType() {
      collectType = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'canType' field.
      * @return The value.
      */
    public java.lang.Long getCanType() {
      return canType;
    }


    /**
      * Sets the value of the 'canType' field.
      * @param value The value of 'canType'.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList.Builder setCanType(java.lang.Long value) {
      validate(fields()[3], value);
      this.canType = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'canType' field has been set.
      * @return True if the 'canType' field has been set, false otherwise.
      */
    public boolean hasCanType() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'canType' field.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList.Builder clearCanType() {
      canType = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'time' field.
      * @return The value.
      */
    public java.lang.CharSequence getTime() {
      return time;
    }


    /**
      * Sets the value of the 'time' field.
      * @param value The value of 'time'.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList.Builder setTime(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.time = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'time' field has been set.
      * @return True if the 'time' field has been set, false otherwise.
      */
    public boolean hasTime() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'time' field.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList.Builder clearTime() {
      time = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'outsideUseDataMap' field.
      * @return The value.
      */
    public java.util.Map<java.lang.CharSequence,java.util.Map<java.lang.CharSequence,java.lang.CharSequence>> getOutsideUseDataMap() {
      return outsideUseDataMap;
    }


    /**
      * Sets the value of the 'outsideUseDataMap' field.
      * @param value The value of 'outsideUseDataMap'.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList.Builder setOutsideUseDataMap(java.util.Map<java.lang.CharSequence,java.util.Map<java.lang.CharSequence,java.lang.CharSequence>> value) {
      validate(fields()[5], value);
      this.outsideUseDataMap = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'outsideUseDataMap' field has been set.
      * @return True if the 'outsideUseDataMap' field has been set, false otherwise.
      */
    public boolean hasOutsideUseDataMap() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'outsideUseDataMap' field.
      * @return This builder.
      */
    public zdf.learn.com.commonUtils.data.avro.schema.can300.CanInformationList.Builder clearOutsideUseDataMap() {
      outsideUseDataMap = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public CanInformationList build() {
      try {
        CanInformationList record = new CanInformationList();
        record.canId = fieldSetFlags()[0] ? this.canId : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.dataLengthAfterCompression = fieldSetFlags()[1] ? this.dataLengthAfterCompression : (java.lang.Long) defaultValue(fields()[1]);
        record.collectType = fieldSetFlags()[2] ? this.collectType : (java.lang.Long) defaultValue(fields()[2]);
        record.canType = fieldSetFlags()[3] ? this.canType : (java.lang.Long) defaultValue(fields()[3]);
        record.time = fieldSetFlags()[4] ? this.time : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.outsideUseDataMap = fieldSetFlags()[5] ? this.outsideUseDataMap : (java.util.Map<java.lang.CharSequence,java.util.Map<java.lang.CharSequence,java.lang.CharSequence>>) defaultValue(fields()[5]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<CanInformationList>
    WRITER$ = (org.apache.avro.io.DatumWriter<CanInformationList>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<CanInformationList>
    READER$ = (org.apache.avro.io.DatumReader<CanInformationList>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    if (this.canId == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.canId);
    }

    if (this.dataLengthAfterCompression == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeLong(this.dataLengthAfterCompression);
    }

    if (this.collectType == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeLong(this.collectType);
    }

    if (this.canType == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeLong(this.canType);
    }

    if (this.time == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.time);
    }

    if (this.outsideUseDataMap == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      long size0 = this.outsideUseDataMap.size();
      out.writeMapStart();
      out.setItemCount(size0);
      long actualSize0 = 0;
      for (java.util.Map.Entry<java.lang.CharSequence, java.util.Map<java.lang.CharSequence,java.lang.CharSequence>> e0: this.outsideUseDataMap.entrySet()) {
        actualSize0++;
        out.startItem();
        out.writeString(e0.getKey());
        java.util.Map<java.lang.CharSequence,java.lang.CharSequence> v0 = e0.getValue();
        long size1 = v0.size();
        out.writeMapStart();
        out.setItemCount(size1);
        long actualSize1 = 0;
        for (java.util.Map.Entry<java.lang.CharSequence, java.lang.CharSequence> e1: v0.entrySet()) {
          actualSize1++;
          out.startItem();
          out.writeString(e1.getKey());
          java.lang.CharSequence v1 = e1.getValue();
          out.writeString(v1);
        }
        out.writeMapEnd();
        if (actualSize1 != size1)
      throw new java.util.ConcurrentModificationException("Map-size written was " + size1 + ", but element count was " + actualSize1 + ".");
      }
      out.writeMapEnd();
      if (actualSize0 != size0)
      throw new java.util.ConcurrentModificationException("Map-size written was " + size0 + ", but element count was " + actualSize0 + ".");
    }

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      if (in.readIndex() != 1) {
        in.readNull();
        this.canId = null;
      } else {
        this.canId = in.readString(this.canId instanceof Utf8 ? (Utf8)this.canId : null);
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.dataLengthAfterCompression = null;
      } else {
        this.dataLengthAfterCompression = in.readLong();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.collectType = null;
      } else {
        this.collectType = in.readLong();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.canType = null;
      } else {
        this.canType = in.readLong();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.time = null;
      } else {
        this.time = in.readString(this.time instanceof Utf8 ? (Utf8)this.time : null);
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.outsideUseDataMap = null;
      } else {
        long size0 = in.readMapStart();
        java.util.Map<java.lang.CharSequence,java.util.Map<java.lang.CharSequence,java.lang.CharSequence>> m0 = this.outsideUseDataMap; // Need fresh name due to limitation of macro system
        if (m0 == null) {
          m0 = new java.util.HashMap<java.lang.CharSequence,java.util.Map<java.lang.CharSequence,java.lang.CharSequence>>((int)size0);
          this.outsideUseDataMap = m0;
        } else m0.clear();
        for ( ; 0 < size0; size0 = in.mapNext()) {
          for ( ; size0 != 0; size0--) {
            java.lang.CharSequence k0 = null;
            k0 = in.readString(k0 instanceof Utf8 ? (Utf8)k0 : null);
            java.util.Map<java.lang.CharSequence,java.lang.CharSequence> v0 = null;
            long size1 = in.readMapStart();
            java.util.Map<java.lang.CharSequence,java.lang.CharSequence> m1 = v0; // Need fresh name due to limitation of macro system
            if (m1 == null) {
              m1 = new java.util.HashMap<java.lang.CharSequence,java.lang.CharSequence>((int)size1);
              v0 = m1;
            } else m1.clear();
            for ( ; 0 < size1; size1 = in.mapNext()) {
              for ( ; size1 != 0; size1--) {
                java.lang.CharSequence k1 = null;
                k1 = in.readString(k1 instanceof Utf8 ? (Utf8)k1 : null);
                java.lang.CharSequence v1 = null;
                v1 = in.readString(v1 instanceof Utf8 ? (Utf8)v1 : null);
                m1.put(k1, v1);
              }
            }
            m0.put(k0, v0);
          }
        }
      }

    } else {
      for (int i = 0; i < 6; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          if (in.readIndex() != 1) {
            in.readNull();
            this.canId = null;
          } else {
            this.canId = in.readString(this.canId instanceof Utf8 ? (Utf8)this.canId : null);
          }
          break;

        case 1:
          if (in.readIndex() != 1) {
            in.readNull();
            this.dataLengthAfterCompression = null;
          } else {
            this.dataLengthAfterCompression = in.readLong();
          }
          break;

        case 2:
          if (in.readIndex() != 1) {
            in.readNull();
            this.collectType = null;
          } else {
            this.collectType = in.readLong();
          }
          break;

        case 3:
          if (in.readIndex() != 1) {
            in.readNull();
            this.canType = null;
          } else {
            this.canType = in.readLong();
          }
          break;

        case 4:
          if (in.readIndex() != 1) {
            in.readNull();
            this.time = null;
          } else {
            this.time = in.readString(this.time instanceof Utf8 ? (Utf8)this.time : null);
          }
          break;

        case 5:
          if (in.readIndex() != 1) {
            in.readNull();
            this.outsideUseDataMap = null;
          } else {
            long size0 = in.readMapStart();
            java.util.Map<java.lang.CharSequence,java.util.Map<java.lang.CharSequence,java.lang.CharSequence>> m0 = this.outsideUseDataMap; // Need fresh name due to limitation of macro system
            if (m0 == null) {
              m0 = new java.util.HashMap<java.lang.CharSequence,java.util.Map<java.lang.CharSequence,java.lang.CharSequence>>((int)size0);
              this.outsideUseDataMap = m0;
            } else m0.clear();
            for ( ; 0 < size0; size0 = in.mapNext()) {
              for ( ; size0 != 0; size0--) {
                java.lang.CharSequence k0 = null;
                k0 = in.readString(k0 instanceof Utf8 ? (Utf8)k0 : null);
                java.util.Map<java.lang.CharSequence,java.lang.CharSequence> v0 = null;
                long size1 = in.readMapStart();
                java.util.Map<java.lang.CharSequence,java.lang.CharSequence> m1 = v0; // Need fresh name due to limitation of macro system
                if (m1 == null) {
                  m1 = new java.util.HashMap<java.lang.CharSequence,java.lang.CharSequence>((int)size1);
                  v0 = m1;
                } else m1.clear();
                for ( ; 0 < size1; size1 = in.mapNext()) {
                  for ( ; size1 != 0; size1--) {
                    java.lang.CharSequence k1 = null;
                    k1 = in.readString(k1 instanceof Utf8 ? (Utf8)k1 : null);
                    java.lang.CharSequence v1 = null;
                    v1 = in.readString(v1 instanceof Utf8 ? (Utf8)v1 : null);
                    m1.put(k1, v1);
                  }
                }
                m0.put(k0, v0);
              }
            }
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










