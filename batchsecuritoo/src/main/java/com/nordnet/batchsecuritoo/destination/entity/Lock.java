package com.nordnet.batchsecuritoo.destination.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "batch_monitoring")
public class Lock implements Serializable {

    @Id
    private Long id;

    @Column(name = "batch_name")
    private String batchName;

    @Column(name = "lock_status")
    private String lockStatus;

    @Column(name = "job_start")
    private Date jobStart;

    @Column(name = "job_end")
    private Date jobEnd;

    @Column(name = "status_at_end")
    private String statusAtEnd;

    public Lock() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(String lockStatus) {
        this.lockStatus = lockStatus;
    }

    public Date getJobStart() {
        return jobStart;
    }

    public void setJobStart(Date jobStart) {
        this.jobStart = jobStart;
    }

    public Date getJobEnd() {
        return jobEnd;
    }

    public void setJobEnd(Date jobEnd) {
        this.jobEnd = jobEnd;
    }

    public String getStatusAtEnd() {
        return statusAtEnd;
    }

    public void setStatusAtEnd(String statusAtEnd) {
        this.statusAtEnd = statusAtEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lock that = (Lock) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Lock{" +
                "id=" + id +
                ", batchName='" + batchName + '\'' +
                ", lockStatus='" + lockStatus + '\'' +
                ", jobStart=" + jobStart +
                ", jobEnd=" + jobEnd +
                ", statusAtEnd='" + statusAtEnd + '\'' +
                '}';
    }
}
