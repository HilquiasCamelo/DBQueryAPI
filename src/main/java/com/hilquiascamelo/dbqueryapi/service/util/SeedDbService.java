package com.hilquiascamelo.dbqueryapi.service.util;

import com.hilquiascamelo.dbqueryapi.entity.Cargo;
import com.hilquiascamelo.dbqueryapi.entity.Users;
import com.hilquiascamelo.dbqueryapi.entity.enums.Perfil;
import com.hilquiascamelo.dbqueryapi.repository.CargoRepository;
import com.hilquiascamelo.dbqueryapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeedDbService {
    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CargoRepository cargoRepository;

    public void seedDatabase ( ) {
        Users
                user =
                new Users( 1 , "Hilquias Camelo" , pe.encode( "123" ) , "hilquiaswpc11@outlook.com" ,
                        Perfil.toEnum( 1 ) , true );
        List < Users > userList = new ArrayList <>( );
        userList.add( user );
        userRepository.saveAll( userList );


        List < Cargo > cargos = new ArrayList <>( );

        cargos.add( new Cargo( true , "Analista de UX/UI" , 43 , "ANA-UXUI" ) );
        cargos.add( new Cargo( true , "Analista de Suporte" , 4 , "ANA-SUP" ) );
        cargos.add( new Cargo( true , "Engenheiro de Software" , 5 , "ENG-SOF" ) );
        cargos.add( new Cargo( true , "Analista de Segurança da Informação" , 21 , "ANA-SEG" ) );
        cargos.add( new Cargo( false , "Analista de Suporte Técnico" , 22 , "ANA-SUP-T" ) );
        cargos.add( new Cargo( true , "Analista de Redes" , 23 , "ANA-RED" ) );
        cargos.add( new Cargo( true , "Analista de Negócios" , 24 , "ANA-NEG" ) );
        cargos.add( new Cargo( false , "Desenvolvedor Front-End" , 25 , "DEV-FE" ) );
        cargos.add( new Cargo( true , "Gerente de Projetos de TI" , 26 , "GER-PROJ-TI" ) );
        cargos.add( new Cargo( true , "Analista de Suporte de Sistemas" , 27 , "ANA-SUP-SIS" ) );
        cargos.add( new Cargo( false , "Desenvolvedor Mobile" , 28 , "DEV-MOB" ) );
        cargos.add( new Cargo( true , "Analista de BI" , 29 , "ANA-BI" ) );
        cargos.add( new Cargo( true , "Arquiteto de Soluções" , 30 , "ARQ-SOL" ) );
        cargos.add( new Cargo( false , "Analista de Testes" , 31 , "ANA-TST" ) );
        cargos.add( new Cargo( true , "Analista de CRM" , 32 , "ANA-CRM" ) );
        cargos.add( new Cargo( true , "Analista de Infraestrutura" , 33 , "ANA-INFRA" ) );
        cargos.add( new Cargo( false , "Desenvolvedor Python" , 34 , "DEV-PY" ) );
        cargos.add( new Cargo( true , "Analista de BI Júnior" , 35 , "ANA-BI-JR" ) );
        cargos.add( new Cargo( true , "Analista de Suporte Júnior" , 36 , "ANA-SUP-JR" ) );
        cargos.add( new Cargo( false , "Desenvolvedor Ruby" , 37 , "DEV-RB" ) );
        cargos.add( new Cargo( true , "Analista de Marketing Digital" , 38 , "ANA-MKT-DIG" ) );
        cargos.add( new Cargo( true , "Analista de Sistemas Sênior" , 39 , "ANA-SIS-SEN" ) );
        cargos.add( new Cargo( true , "Analista de Dados" , 40 , "ANA-DAD" ) );
        cargos.add( new Cargo( false , "Desenvolvedor Java" , 41 , "DEV-JV" ) );
        cargos.add( new Cargo( true , "Analista de UX/UI" , 42 , "ANA-UXUI" ) );
        cargos.add( new Cargo( true , "Analista de Cloud Computing" , 43 , "ANA-CLOUD" ) );
        cargos.add( new Cargo( false , "Desenvolvedor C#" , 44 , "DEV-CS" ) );
        cargos.add( new Cargo( true , "Analista de Telecom" , 45 , "ANA-TELECOM" ) );
        cargos.add( new Cargo( true , "Analista de Sistemas Júnior" , 46 , "ANA-SIS-JR" ) );
        cargos.add( new Cargo( false , "Desenvolvedor PHP" , 47 , "DEV-PHP" ) );
        cargos.add( new Cargo( true , "Gerente de Projetos" , 48 , "GER-PROJ" ) );
        cargos.add( new Cargo( true , "Analista de Redes Júnior" , 49 , "ANA-RED-JR" ) );
        cargos.add( new Cargo( false , "Desenvolvedor .NET" , 50 , "DEV-NET" ) );
        cargos.add( new Cargo( true , "Analista de Segurança de Redes" , 51 , "ANA-SEG-RED" ) );
        cargos.add( new Cargo( true , "Analista de Testes Automatizados" , 52 , "ANA-TST-AUT" ) );
        cargos.add( new Cargo( false , "Desenvolvedor Node.js" , 53 , "DEV-NODE" ) );
        cargos.add( new Cargo( true , "Analista de Infraestrutura de Redes" , 54 , "ANA-INFRA-RED" ) );
        cargos.add( new Cargo( true , "Analista de Suporte de Redes" , 55 , "ANA-SUP-RED" ) );
        cargos.add( new Cargo( false , "Desenvolvedor Kotlin" , 56 , "DEV-KOTLIN" ) );
        cargos.add( new Cargo( true , "Analista de DevOps" , 57 , "ANA-DEVOPS" ) );
        cargos.add( new Cargo( true , "Analista de Sistemas Pleno" , 58 , "ANA-SIS-PL" ) );
        cargos.add( new Cargo( false , "Desenvolvedor React" , 59 , "DEV-REACT" ) );
        cargos.add( new Cargo( true , "Analista de Negócios Sênior" , 60 , "ANA-NEG-SEN" ) );
        cargos.add( new Cargo( true , "Analista de Suporte Técnico Júnior" , 61 , "ANA-SUP-T-JR" ) );
        cargos.add( new Cargo( false , "Desenvolvedor Swift" , 62 , "DEV-SWIFT" ) );
        cargos.add( new Cargo( true , "Analista de Banco de Dados" , 63 , "ANA-BD" ) );
        cargos.add( new Cargo( true , "Analista de Sistemas Sênior II" , 64 , "ANA-SIS-SEN-II" ) );
        cargos.add( new Cargo( false , "Desenvolvedor Angular" , 65 , "DEV-ANGULAR" ) );
        cargos.add( new Cargo( true , "Analista de Suporte de Redes Júnior" , 66 , "ANA-SUP-RED-JR" ) );
        cargos.add( new Cargo( true , "Analista de Infraestrutura de Redes Júnior" , 67 , "ANA-INFRA-RED" ) );

        cargoRepository.saveAll( cargos );
    }
}
