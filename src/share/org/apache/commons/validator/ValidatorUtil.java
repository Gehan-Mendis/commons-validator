/*
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2002 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Commons", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */


package org.apache.commons.validator;

import org.apache.commons.beanutils.PropertyUtils;


/**
 * <p>This class helps provides some useful methods for retrieving objects 
 * from different scopes of the application.</p>
 *
 * @author David Winterfeldt
*/
public class ValidatorUtil  {

   /**
    * <p>Replace part of a <code>String</code> with another value.</p>
    *
    * @param	value		<code>String</code> to perform the replacement on.
    * @param	key		The name of the constant.
    * @param	replaceValue	The value of the constant.
   */
   public static String replace(String value, String key, String replaceValue) {
      if (value != null && key != null && replaceValue != null) {
         int pos = value.indexOf(key);
          
         if (pos >= 0) {
            int length = value.length();
            int start = pos;
            int end = pos + key.length();
         
            if (length == key.length()) {
               value = replaceValue;
            } else if (end == length) {
               value = value.substring(0, start) + replaceValue; //+ value.substring(end);
            } else {
            	value = value.substring(0, start) + replaceValue + replace(value.substring(end), key, replaceValue);
            }
         }
      }
      return value;
   }

   /**
    * Convenience method for getting a value from a bean property as a <code>String</code>.
   */
   public static String getValueAsString(Object bean, String property) {
      Object value = null;

      try {
         value = PropertyUtils.getProperty(bean, property);	
      } catch (Exception e) {
         //log("ValidatorUtil::getValueAsString() - " + e.getMessage(), e);
      }
   	
      return (value != null ? value.toString() : null);    	
   }
   
}
