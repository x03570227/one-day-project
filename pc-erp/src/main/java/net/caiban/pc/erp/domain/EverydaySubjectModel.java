package net.caiban.pc.erp.domain;

import java.math.BigDecimal;

/**
 * Created by mar on 16/5/3.
 */
public class EverydaySubjectModel extends EverydaySubject {

    private BigDecimal nowDayPercent;
    private Integer subjectIndex;
    private Integer nextTarget;
    private Integer maxDayIndex;

    public BigDecimal getNowDayPercent() {
        return nowDayPercent;
    }

    public void setNowDayPercent(BigDecimal nowDayPercent) {
        this.nowDayPercent = nowDayPercent;
    }

    public Integer getSubjectIndex() {
        return subjectIndex;
    }

    public void setSubjectIndex(Integer subjectIndex) {
        this.subjectIndex = subjectIndex;
    }

    public Integer getNextTarget() {
        return nextTarget;
    }

    public void setNextTarget(Integer nextTarget) {
        this.nextTarget = nextTarget;
    }

    public Integer getMaxDayIndex() {
        return maxDayIndex;
    }

    public void setMaxDayIndex(Integer maxDayIndex) {
        this.maxDayIndex = maxDayIndex;
    }
}
