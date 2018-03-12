/*
 * Workspace API
 * Agent API
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.genesys.internal.workspace.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * MediaemailinteractionsidsaveData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-03-12T18:04:57.993Z")
public class MediaemailinteractionsidsaveData {
  @SerializedName("body")
  private String body = null;

  @SerializedName("bodyAsPlaintText")
  private String bodyAsPlaintText = null;

  @SerializedName("mime")
  private String mime = null;

  @SerializedName("subject")
  private String subject = null;

  @SerializedName("comment")
  private String comment = null;

  @SerializedName("from")
  private String from = null;

  @SerializedName("to")
  private List<String> to = new ArrayList<String>();

  @SerializedName("cc")
  private List<String> cc = null;

  @SerializedName("bcc")
  private List<String> bcc = null;

  public MediaemailinteractionsidsaveData body(String body) {
    this.body = body;
    return this;
  }

   /**
   * HTML body of email.
   * @return body
  **/
  @ApiModelProperty(value = "HTML body of email.")
  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public MediaemailinteractionsidsaveData bodyAsPlaintText(String bodyAsPlaintText) {
    this.bodyAsPlaintText = bodyAsPlaintText;
    return this;
  }

   /**
   * Plain text body of email.
   * @return bodyAsPlaintText
  **/
  @ApiModelProperty(value = "Plain text body of email.")
  public String getBodyAsPlaintText() {
    return bodyAsPlaintText;
  }

  public void setBodyAsPlaintText(String bodyAsPlaintText) {
    this.bodyAsPlaintText = bodyAsPlaintText;
  }

  public MediaemailinteractionsidsaveData mime(String mime) {
    this.mime = mime;
    return this;
  }

   /**
   * Multipurpose internet mail extensions of email.
   * @return mime
  **/
  @ApiModelProperty(value = "Multipurpose internet mail extensions of email.")
  public String getMime() {
    return mime;
  }

  public void setMime(String mime) {
    this.mime = mime;
  }

  public MediaemailinteractionsidsaveData subject(String subject) {
    this.subject = subject;
    return this;
  }

   /**
   * Subject of email.
   * @return subject
  **/
  @ApiModelProperty(value = "Subject of email.")
  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public MediaemailinteractionsidsaveData comment(String comment) {
    this.comment = comment;
    return this;
  }

   /**
   * Subject of email.
   * @return comment
  **/
  @ApiModelProperty(value = "Subject of email.")
  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public MediaemailinteractionsidsaveData from(String from) {
    this.from = from;
    return this;
  }

   /**
   * from address.
   * @return from
  **/
  @ApiModelProperty(required = true, value = "from address.")
  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public MediaemailinteractionsidsaveData to(List<String> to) {
    this.to = to;
    return this;
  }

  public MediaemailinteractionsidsaveData addToItem(String toItem) {
    this.to.add(toItem);
    return this;
  }

   /**
   * to addresses
   * @return to
  **/
  @ApiModelProperty(required = true, value = "to addresses")
  public List<String> getTo() {
    return to;
  }

  public void setTo(List<String> to) {
    this.to = to;
  }

  public MediaemailinteractionsidsaveData cc(List<String> cc) {
    this.cc = cc;
    return this;
  }

  public MediaemailinteractionsidsaveData addCcItem(String ccItem) {
    if (this.cc == null) {
      this.cc = new ArrayList<String>();
    }
    this.cc.add(ccItem);
    return this;
  }

   /**
   * cc addresses
   * @return cc
  **/
  @ApiModelProperty(value = "cc addresses")
  public List<String> getCc() {
    return cc;
  }

  public void setCc(List<String> cc) {
    this.cc = cc;
  }

  public MediaemailinteractionsidsaveData bcc(List<String> bcc) {
    this.bcc = bcc;
    return this;
  }

  public MediaemailinteractionsidsaveData addBccItem(String bccItem) {
    if (this.bcc == null) {
      this.bcc = new ArrayList<String>();
    }
    this.bcc.add(bccItem);
    return this;
  }

   /**
   * bcc addresses
   * @return bcc
  **/
  @ApiModelProperty(value = "bcc addresses")
  public List<String> getBcc() {
    return bcc;
  }

  public void setBcc(List<String> bcc) {
    this.bcc = bcc;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MediaemailinteractionsidsaveData mediaemailinteractionsidsaveData = (MediaemailinteractionsidsaveData) o;
    return Objects.equals(this.body, mediaemailinteractionsidsaveData.body) &&
        Objects.equals(this.bodyAsPlaintText, mediaemailinteractionsidsaveData.bodyAsPlaintText) &&
        Objects.equals(this.mime, mediaemailinteractionsidsaveData.mime) &&
        Objects.equals(this.subject, mediaemailinteractionsidsaveData.subject) &&
        Objects.equals(this.comment, mediaemailinteractionsidsaveData.comment) &&
        Objects.equals(this.from, mediaemailinteractionsidsaveData.from) &&
        Objects.equals(this.to, mediaemailinteractionsidsaveData.to) &&
        Objects.equals(this.cc, mediaemailinteractionsidsaveData.cc) &&
        Objects.equals(this.bcc, mediaemailinteractionsidsaveData.bcc);
  }

  @Override
  public int hashCode() {
    return Objects.hash(body, bodyAsPlaintText, mime, subject, comment, from, to, cc, bcc);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MediaemailinteractionsidsaveData {\n");
    
    sb.append("    body: ").append(toIndentedString(body)).append("\n");
    sb.append("    bodyAsPlaintText: ").append(toIndentedString(bodyAsPlaintText)).append("\n");
    sb.append("    mime: ").append(toIndentedString(mime)).append("\n");
    sb.append("    subject: ").append(toIndentedString(subject)).append("\n");
    sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
    sb.append("    from: ").append(toIndentedString(from)).append("\n");
    sb.append("    to: ").append(toIndentedString(to)).append("\n");
    sb.append("    cc: ").append(toIndentedString(cc)).append("\n");
    sb.append("    bcc: ").append(toIndentedString(bcc)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

