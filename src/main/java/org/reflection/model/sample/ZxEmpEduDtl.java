package org.reflection.model.sample;

import com.oith.annotation.MacFile;
import com.oith.annotation.MacImagable;
import com.oith.annotation.MacSearchable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Past;
import javax.xml.bind.annotation.XmlRootElement;
import org.reflection.model.com.AbstractEntity;

@Entity
@Table(name = "ZX_EMP_EDU_DTL", catalog = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ZX_EMP_ID", "EXAM"})})
@XmlRootElement
public class ZxEmpEduDtl extends AbstractEntity {

    @Basic(optional = false)
    @Column(name = "EXAM", nullable = false, length = 20)
    private String exam;
    @MacSearchable
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    @Past
    private Date examOrientDate;
    @Column(length = 500)
    private String remarks;
    @Column(name = "SL_NO")
    private Integer slNo;
    @MacFile(min = 1, max = 1000000, extention = "doc, pdf, docx")
    @Column(name = "CERTIFICATE_FILE", length = 255)
    private String certificateFile;
    @MacImagable
    @Column(name = "PIC_FILE", length = 255)
    private String picFile;
    @JoinColumn(name = "ZX_EMP_ID",  nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ZxEmp zxEmp;
    @JoinColumn(name = "ZX_EMP_WHO_CHECKED_BY_ID",  nullable = true)
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private ZxEmp zxEmpWhoCheckedBy;

    public ZxEmpEduDtl() {
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public Date getExamOrientDate() {
        return examOrientDate;
    }

    public void setExamOrientDate(Date examOrientDate) {
        this.examOrientDate = examOrientDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getSlNo() {
        return slNo;
    }

    public void setSlNo(Integer slNo) {
        this.slNo = slNo;
    }

    public String getCertificateFile() {
        return certificateFile;
    }

    public void setCertificateFile(String certificateFile) {
        this.certificateFile = certificateFile;
    }

    public String getPicFile() {
        return picFile;
    }

    public void setPicFile(String picFile) {
        this.picFile = picFile;
    }

    public ZxEmp getZxEmp() {
        return zxEmp;
    }

    public void setZxEmp(ZxEmp zxEmp) {
        this.zxEmp = zxEmp;
    }

    public ZxEmp getZxEmpWhoCheckedBy() {
        return zxEmpWhoCheckedBy;
    }

    public void setZxEmpWhoCheckedBy(ZxEmp zxEmpWhoCheckedBy) {
        this.zxEmpWhoCheckedBy = zxEmpWhoCheckedBy;
    }

}
