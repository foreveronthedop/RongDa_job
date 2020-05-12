package com.llm.demo.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "`t_announcement`")
public class Announcement implements Serializable {
    @Id
    @Column(name = "`id`")
    private Integer id;

    @Column(name = "`indexNo`")
    private String indexno;

    @Column(name = "`category`")
    private String category;

    @Column(name = "`organization`")
    private String organization;

    @Column(name = "`creattime`")
    private String creattime;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`roles`")
    private String roles;

    @Column(name = "`content`")
    private String content;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return indexNo
     */
    public String getIndexno() {
        return indexno;
    }

    /**
     * @param indexno
     */
    public void setIndexno(String indexno) {
        this.indexno = indexno == null ? null : indexno.trim();
    }

    /**
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category
     */
    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    /**
     * @return organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * @param organization
     */
    public void setOrganization(String organization) {
        this.organization = organization == null ? null : organization.trim();
    }

    /**
     * @return creattime
     */
    public String getCreattime() {
        return creattime;
    }

    /**
     * @param creattime
     */
    public void setCreattime(String creattime) {
        this.creattime = creattime == null ? null : creattime.trim();
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return roles
     */
    public String getRoles() {
        return roles;
    }

    /**
     * @param roles
     */
    public void setRoles(String roles) {
        this.roles = roles == null ? null : roles.trim();
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "id=" + id +
                ", indexno='" + indexno + '\'' +
                ", category='" + category + '\'' +
                ", organization='" + organization + '\'' +
                ", creattime='" + creattime + '\'' +
                ", name='" + name + '\'' +
                ", roles='" + roles + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}