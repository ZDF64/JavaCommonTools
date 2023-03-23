# JavaCommonTools
D:.
│  .classpath
│  .gitignore
│  .project
│  pom.xml
│  tree.txt
├─libs
│  │  README.me
│  │  
│  └─zdf
│      └─learn
│          └─com
│              └─commonUtils
│                  └─data
│                      └─avro
│                          └─schema
│                              ├─can300
│                              │      Can300_19nev.java
│                              │      CanFrameNumber.java
│                              │      CanInformationList.java
│                              │      CommonHeader.java
│                              │      DataCapacity.java
│                              │      Dcm19Message.java
│                              │      GPS.java
│                              │      MM.java
│                              │      Point.java
│                              │      TimeAndCoordinate.java
│                              │      Type3OutsideUseData.java
│                              │      UpdCondition.java
│                              │      VehicleInformationHeader.java
│                              │      
│                              └─can300_19nev
│                                      DecoderWarningList.java
│                                      DecodeWarningStatus.java
│                                      
└─src
   ├─main
   │  ├─java
   │  │  └─zdf
   │  │      └─learn
   │  │          └─com
   │  │              └─commonUtils
   │  │                  │  App.java
   │  │                  │  AppConfig.java
   │  │                  │  
   │  │                  ├─data
   │  │                  │  │  MakeAvroData.java
   │  │                  │  │  package-info.java
   │  │                  │  │  ProtoData.java
   │  │                  │  │  
   │  │                  │  ├─avro
   │  │                  │  │  └─schema
   │  │                  │  │      │  Can300_19nev.asvc
   │  │                  │  │      │  package-info.java
   │  │                  │  │      │  
   │  │                  │  │      ├─can300
   │  │                  │  │      │      Can300_19nev.java
   │  │                  │  │      │      CanFrameNumber.java
   │  │                  │  │      │      CanInformationList.java
   │  │                  │  │      │      CommonHeader.java
   │  │                  │  │      │      DataCapacity.java
   │  │                  │  │      │      Dcm19Message.java
   │  │                  │  │      │      GPS.java
   │  │                  │  │      │      MM.java
   │  │                  │  │      │      Point.java
   │  │                  │  │      │      TimeAndCoordinate.java
   │  │                  │  │      │      Type3OutsideUseData.java
   │  │                  │  │      │      UpdCondition.java
   │  │                  │  │      │      VehicleInformationHeader.java
   │  │                  │  │      │      
   │  │                  │  │      └─can300_19nev
   │  │                  │  │              DecoderWarningList.java
   │  │                  │  │              DecodeWarningStatus.java
   │  │                  │  │              
   │  │                  │  ├─parquet
   │  │                  │  └─protobuf
   │  │                  │          Demo.java
   │  │                  │          FileBean.java
   │  │                  │          FileBeanOrBuilder.java
   │  │                  │          FileDeal.java
   │  │                  │          FileDealOrBuilder.java
   │  │                  │          
   │  │                  ├─DataBase
   │  │                  │      package-info.java
   │  │                  │      
   │  │                  ├─Files
   │  │                  │      DefangFileHandle.java
   │  │                  │      package-info.java
   │  │                  │      
   │  │                  ├─IO
   │  │                  │      package-info.java
   │  │                  │      
   │  │                  ├─MultiTask
   │  │                  │      package-info.java
   │  │                  │      
   │  │                  ├─proxy
   │  │                  │  ├─DynamicsProxy
   │  │                  │  │      DKRoom.java
   │  │                  │  │      DynamicsProxyTest.java
   │  │                  │  │      House.java
   │  │                  │  │      Person.java
   │  │                  │  │      Renter.java
   │  │                  │  │      RenterInvocationHandler.java
   │  │                  │  │      
   │  │                  │  └─staticProxy
   │  │                  │          Owner.java
   │  │                  │          Person.java
   │  │                  │          Renter.java
   │  │                  │          RenterProxy.java
   │  │                  │          TestProxy.java
   │  │                  │          
   │  │                  ├─tags
   │  │                  │  │  package-info.java
   │  │                  │  │  RetryCovery.java
   │  │                  │  │  RetryCoveryAop.java
   │  │                  │  │  TimerCut.java
   │  │                  │  │  TimerCutAop.java
   │  │                  │  │  
   │  │                  │  └─enums
   │  │                  │          MonitorType.java
   │  │                  │          package-info.java
   │  │                  │          
   │  │                  └─tools
   │  │                          ComputeTools.java
   │  │                          package-info.java
   │  │                          
   │  └─resource
   │          demo.proto
   │          log4j.properties
   │          
   └─test
       └─java
           └─zdf
               └─learn
                   └─com
                       └─commonUtils
                               AppTest.java
