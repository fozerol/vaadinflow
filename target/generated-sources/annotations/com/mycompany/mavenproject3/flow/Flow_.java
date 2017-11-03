package com.mycompany.mavenproject3.flow;

import com.mycompany.mavenproject3.flow.FlowFormData;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-31T14:48:40")
@StaticMetamodel(Flow.class)
public class Flow_ { 

    public static volatile SingularAttribute<Flow, String> senderId;
    public static volatile SingularAttribute<Flow, String> receiverId;
    public static volatile SingularAttribute<Flow, Date> sendDate;
    public static volatile ListAttribute<Flow, FlowFormData> flowFormData;
    public static volatile SingularAttribute<Flow, String> starterId;
    public static volatile SingularAttribute<Flow, Long> id;
    public static volatile SingularAttribute<Flow, String> flowform;
    public static volatile SingularAttribute<Flow, Date> startDate;

}