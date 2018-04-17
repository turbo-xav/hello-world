/**
 * SayHelloResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.netroxtech.webservice;

public class SayHelloResponse  implements java.io.Serializable {
    private java.lang.String sayHelloReturn;

    public SayHelloResponse() {
    }

    public SayHelloResponse(
           java.lang.String sayHelloReturn) {
           this.sayHelloReturn = sayHelloReturn;
    }


    /**
     * Gets the sayHelloReturn value for this SayHelloResponse.
     * 
     * @return sayHelloReturn
     */
    public java.lang.String getSayHelloReturn() {
        return sayHelloReturn;
    }


    /**
     * Sets the sayHelloReturn value for this SayHelloResponse.
     * 
     * @param sayHelloReturn
     */
    public void setSayHelloReturn(java.lang.String sayHelloReturn) {
        this.sayHelloReturn = sayHelloReturn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SayHelloResponse)) return false;
        SayHelloResponse other = (SayHelloResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sayHelloReturn==null && other.getSayHelloReturn()==null) || 
             (this.sayHelloReturn!=null &&
              this.sayHelloReturn.equals(other.getSayHelloReturn())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getSayHelloReturn() != null) {
            _hashCode += getSayHelloReturn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SayHelloResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice.netroxtech.com", ">sayHelloResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sayHelloReturn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://webservice.netroxtech.com", "sayHelloReturn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
