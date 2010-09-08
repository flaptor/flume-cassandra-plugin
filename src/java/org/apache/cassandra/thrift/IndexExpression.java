/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package org.apache.cassandra.thrift;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.thrift.*;
import org.apache.thrift.meta_data.*;
import org.apache.thrift.protocol.*;

public class IndexExpression implements TBase<IndexExpression._Fields>, java.io.Serializable, Cloneable, Comparable<IndexExpression> {
  private static final TStruct STRUCT_DESC = new TStruct("IndexExpression");

  private static final TField COLUMN_NAME_FIELD_DESC = new TField("column_name", TType.STRING, (short)1);
  private static final TField OP_FIELD_DESC = new TField("op", TType.I32, (short)2);
  private static final TField VALUE_FIELD_DESC = new TField("value", TType.STRING, (short)3);

  public byte[] column_name;
  /**
   * 
   * @see IndexOperator
   */
  public IndexOperator op;
  public byte[] value;

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements TFieldIdEnum {
    COLUMN_NAME((short)1, "column_name"),
    /**
     * 
     * @see IndexOperator
     */
    OP((short)2, "op"),
    VALUE((short)3, "value");

    private static final Map<Integer, _Fields> byId = new HashMap<Integer, _Fields>();
    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byId.put((int)field._thriftId, field);
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      return byId.get(fieldId);
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments

  public static final Map<_Fields, FieldMetaData> metaDataMap = Collections.unmodifiableMap(new EnumMap<_Fields, FieldMetaData>(_Fields.class) {{
    put(_Fields.COLUMN_NAME, new FieldMetaData("column_name", TFieldRequirementType.REQUIRED, 
        new FieldValueMetaData(TType.STRING)));
    put(_Fields.OP, new FieldMetaData("op", TFieldRequirementType.REQUIRED, 
        new EnumMetaData(TType.ENUM, IndexOperator.class)));
    put(_Fields.VALUE, new FieldMetaData("value", TFieldRequirementType.REQUIRED, 
        new FieldValueMetaData(TType.STRING)));
  }});

  static {
    FieldMetaData.addStructMetaDataMap(IndexExpression.class, metaDataMap);
  }

  public IndexExpression() {
  }

  public IndexExpression(
    byte[] column_name,
    IndexOperator op,
    byte[] value)
  {
    this();
    this.column_name = column_name;
    this.op = op;
    this.value = value;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public IndexExpression(IndexExpression other) {
    if (other.isSetColumn_name()) {
      this.column_name = new byte[other.column_name.length];
      System.arraycopy(other.column_name, 0, column_name, 0, other.column_name.length);
    }
    if (other.isSetOp()) {
      this.op = other.op;
    }
    if (other.isSetValue()) {
      this.value = new byte[other.value.length];
      System.arraycopy(other.value, 0, value, 0, other.value.length);
    }
  }

  public IndexExpression deepCopy() {
    return new IndexExpression(this);
  }

  @Deprecated
  public IndexExpression clone() {
    return new IndexExpression(this);
  }

  public byte[] getColumn_name() {
    return this.column_name;
  }

  public IndexExpression setColumn_name(byte[] column_name) {
    this.column_name = column_name;
    return this;
  }

  public void unsetColumn_name() {
    this.column_name = null;
  }

  /** Returns true if field column_name is set (has been asigned a value) and false otherwise */
  public boolean isSetColumn_name() {
    return this.column_name != null;
  }

  public void setColumn_nameIsSet(boolean value) {
    if (!value) {
      this.column_name = null;
    }
  }

  /**
   * 
   * @see IndexOperator
   */
  public IndexOperator getOp() {
    return this.op;
  }

  /**
   * 
   * @see IndexOperator
   */
  public IndexExpression setOp(IndexOperator op) {
    this.op = op;
    return this;
  }

  public void unsetOp() {
    this.op = null;
  }

  /** Returns true if field op is set (has been asigned a value) and false otherwise */
  public boolean isSetOp() {
    return this.op != null;
  }

  public void setOpIsSet(boolean value) {
    if (!value) {
      this.op = null;
    }
  }

  public byte[] getValue() {
    return this.value;
  }

  public IndexExpression setValue(byte[] value) {
    this.value = value;
    return this;
  }

  public void unsetValue() {
    this.value = null;
  }

  /** Returns true if field value is set (has been asigned a value) and false otherwise */
  public boolean isSetValue() {
    return this.value != null;
  }

  public void setValueIsSet(boolean value) {
    if (!value) {
      this.value = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case COLUMN_NAME:
      if (value == null) {
        unsetColumn_name();
      } else {
        setColumn_name((byte[])value);
      }
      break;

    case OP:
      if (value == null) {
        unsetOp();
      } else {
        setOp((IndexOperator)value);
      }
      break;

    case VALUE:
      if (value == null) {
        unsetValue();
      } else {
        setValue((byte[])value);
      }
      break;

    }
  }

  public void setFieldValue(int fieldID, Object value) {
    setFieldValue(_Fields.findByThriftIdOrThrow(fieldID), value);
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case COLUMN_NAME:
      return getColumn_name();

    case OP:
      return getOp();

    case VALUE:
      return getValue();

    }
    throw new IllegalStateException();
  }

  public Object getFieldValue(int fieldId) {
    return getFieldValue(_Fields.findByThriftIdOrThrow(fieldId));
  }

  /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    switch (field) {
    case COLUMN_NAME:
      return isSetColumn_name();
    case OP:
      return isSetOp();
    case VALUE:
      return isSetValue();
    }
    throw new IllegalStateException();
  }

  public boolean isSet(int fieldID) {
    return isSet(_Fields.findByThriftIdOrThrow(fieldID));
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof IndexExpression)
      return this.equals((IndexExpression)that);
    return false;
  }

  public boolean equals(IndexExpression that) {
    if (that == null)
      return false;

    boolean this_present_column_name = true && this.isSetColumn_name();
    boolean that_present_column_name = true && that.isSetColumn_name();
    if (this_present_column_name || that_present_column_name) {
      if (!(this_present_column_name && that_present_column_name))
        return false;
      if (!java.util.Arrays.equals(this.column_name, that.column_name))
        return false;
    }

    boolean this_present_op = true && this.isSetOp();
    boolean that_present_op = true && that.isSetOp();
    if (this_present_op || that_present_op) {
      if (!(this_present_op && that_present_op))
        return false;
      if (!this.op.equals(that.op))
        return false;
    }

    boolean this_present_value = true && this.isSetValue();
    boolean that_present_value = true && that.isSetValue();
    if (this_present_value || that_present_value) {
      if (!(this_present_value && that_present_value))
        return false;
      if (!java.util.Arrays.equals(this.value, that.value))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(IndexExpression other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    IndexExpression typedOther = (IndexExpression)other;

    lastComparison = Boolean.valueOf(isSetColumn_name()).compareTo(isSetColumn_name());
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = TBaseHelper.compareTo(column_name, typedOther.column_name);
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = Boolean.valueOf(isSetOp()).compareTo(isSetOp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = TBaseHelper.compareTo(op, typedOther.op);
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = Boolean.valueOf(isSetValue()).compareTo(isSetValue());
    if (lastComparison != 0) {
      return lastComparison;
    }
    lastComparison = TBaseHelper.compareTo(value, typedOther.value);
    if (lastComparison != 0) {
      return lastComparison;
    }
    return 0;
  }

  public void read(TProtocol iprot) throws TException {
    TField field;
    iprot.readStructBegin();
    while (true)
    {
      field = iprot.readFieldBegin();
      if (field.type == TType.STOP) { 
        break;
      }
      _Fields fieldId = _Fields.findByThriftId(field.id);
      if (fieldId == null) {
        TProtocolUtil.skip(iprot, field.type);
      } else {
        switch (fieldId) {
          case COLUMN_NAME:
            if (field.type == TType.STRING) {
              this.column_name = iprot.readBinary();
            } else { 
              TProtocolUtil.skip(iprot, field.type);
            }
            break;
          case OP:
            if (field.type == TType.I32) {
              this.op = IndexOperator.findByValue(iprot.readI32());
            } else { 
              TProtocolUtil.skip(iprot, field.type);
            }
            break;
          case VALUE:
            if (field.type == TType.STRING) {
              this.value = iprot.readBinary();
            } else { 
              TProtocolUtil.skip(iprot, field.type);
            }
            break;
        }
        iprot.readFieldEnd();
      }
    }
    iprot.readStructEnd();

    // check for required fields of primitive type, which can't be checked in the validate method
    validate();
  }

  public void write(TProtocol oprot) throws TException {
    validate();

    oprot.writeStructBegin(STRUCT_DESC);
    if (this.column_name != null) {
      oprot.writeFieldBegin(COLUMN_NAME_FIELD_DESC);
      oprot.writeBinary(this.column_name);
      oprot.writeFieldEnd();
    }
    if (this.op != null) {
      oprot.writeFieldBegin(OP_FIELD_DESC);
      oprot.writeI32(this.op.getValue());
      oprot.writeFieldEnd();
    }
    if (this.value != null) {
      oprot.writeFieldBegin(VALUE_FIELD_DESC);
      oprot.writeBinary(this.value);
      oprot.writeFieldEnd();
    }
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("IndexExpression(");
    boolean first = true;

    sb.append("column_name:");
    if (this.column_name == null) {
      sb.append("null");
    } else {
        int __column_name_size = Math.min(this.column_name.length, 128);
        for (int i = 0; i < __column_name_size; i++) {
          if (i != 0) sb.append(" ");
          sb.append(Integer.toHexString(this.column_name[i]).length() > 1 ? Integer.toHexString(this.column_name[i]).substring(Integer.toHexString(this.column_name[i]).length() - 2).toUpperCase() : "0" + Integer.toHexString(this.column_name[i]).toUpperCase());
        }
        if (this.column_name.length > 128) sb.append(" ...");
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("op:");
    if (this.op == null) {
      sb.append("null");
    } else {
      String op_name = op.name();
      if (op_name != null) {
        sb.append(op_name);
        sb.append(" (");
      }
      sb.append(this.op);
      if (op_name != null) {
        sb.append(")");
      }
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("value:");
    if (this.value == null) {
      sb.append("null");
    } else {
        int __value_size = Math.min(this.value.length, 128);
        for (int i = 0; i < __value_size; i++) {
          if (i != 0) sb.append(" ");
          sb.append(Integer.toHexString(this.value[i]).length() > 1 ? Integer.toHexString(this.value[i]).substring(Integer.toHexString(this.value[i]).length() - 2).toUpperCase() : "0" + Integer.toHexString(this.value[i]).toUpperCase());
        }
        if (this.value.length > 128) sb.append(" ...");
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
    if (column_name == null) {
      throw new TProtocolException("Required field 'column_name' was not present! Struct: " + toString());
    }
    if (op == null) {
      throw new TProtocolException("Required field 'op' was not present! Struct: " + toString());
    }
    if (value == null) {
      throw new TProtocolException("Required field 'value' was not present! Struct: " + toString());
    }
  }

}

