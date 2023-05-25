package com.fadcr.agent.entity.method;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document("Metrics")
public class Metric implements Serializable {

    private String name;
    private int AD;
    private int CBO;
    private int CC;
    private int CCL;
    private int CCO;
    private int CI;
    private int CLC;
    private int CLLC;
    private int CLOC;
    private int DIT;
    private int DLOC;
    private int LCOM5;
    private int LDC;
    private int LLDC;
    private int LLOC;
    private int LOC;
    private int NG;
    private int NLG;
    private int NLM;
    private int NLPM;
    private int NLS;
    private int NM;
    private int NOA;
    private int NOC;
    private int NOD;
    private int NOI;
    private int NOP;
    private int NOS;
    private int NPM;
    private int NS;
    private int PDA;
    private int PUA;
    private int RFC;
    private int TCLOC;
    private int TLLOC;
    private int TLOC;
    private int TNG;
    private int TNLG;
    private int TNLM;
    private int TNLPM;
    private int TNLS;
    private int TNM;
    private int TNOS;
    private int TNPM;
    private int TNS;
    private int WMC;
}






