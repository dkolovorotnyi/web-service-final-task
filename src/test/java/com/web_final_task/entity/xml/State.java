package com.web_final_task.entity.xml;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "state")
@XmlEnum
public enum State {

    AA,
    AE,
    AK,
    AL,
    AP,
    AR,
    AS,
    AZ,
    CA,
    CO,
    CT,
    DC,
    DE,
    FL,
    FM,
    GA,
    GU,
    HI,
    IA,
    ID,
    IL,
    IN,
    KS,
    KY,
    LA,
    MA,
    MD,
    ME,
    MH,
    MI,
    MN,
    MO,
    MP,
    MS,
    MT,
    NC,
    ND,
    NE,
    NH,
    NM,
    NV,
    NY,
    OH,
    OK,
    OR,
    PA,
    PR,
    PW,
    RI,
    SC,
    SD,
    TN,
    TX,
    UT,
    VA,
    VI,
    VT,
    WA,
    WI,
    WV,
    WY;

    public String value() {
        return name();
    }

    public static State fromValue(String v) {
        return valueOf(v);
    }

}
