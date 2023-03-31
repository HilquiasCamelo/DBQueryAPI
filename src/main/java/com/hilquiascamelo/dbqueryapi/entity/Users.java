package com.hilquiascamelo.dbqueryapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hilquiascamelo.dbqueryapi.entity.enums.Perfil;
import org.springframework.context.annotation.Profile;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.*;


@Entity
@Inheritance ( strategy = InheritanceType.JOINED)
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Integer idUsers;

    private String name;

    @JsonIgnore
    private String password;

    private String email;

    private Integer type;

    private boolean situation;


    @ElementCollection ( fetch = FetchType.EAGER)
    @CollectionTable ( name = "Perfil")
    private Set < Integer > profiles = new HashSet <>( );

    public Users ( ) {
        addPerfi( Perfil.USER );
    }

    /**
     * @param idUsers
     * @param name
     * @param password
     * @param email
     * @param type
     * @param situation
     */
    public Users (
            Integer idUsers ,
            String name ,
            String password ,
            String email ,
            Perfil type ,
            boolean situation
                 ) {
        this.idUsers = idUsers;
        this.name = name;
        this.password = password;
        this.email = email;
        this.type = (type == null) ? null : type.getCod( );
        this.situation = situation;
        addPerfi( Perfil.USER );
    }

    public Integer getIdUsers ( ) {
        return idUsers;
    }

    public void setIdUsers ( Integer idUsers ) {
        this.idUsers = idUsers;
    }

    public String getName ( ) {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getPassword ( ) {
        return password;
    }

    public void setPassword ( String password ) {
        this.password = password;
    }

    public String getEmail ( ) {
        return email;
    }

    public void setEmail ( String email ) {
        this.email = email;
    }

    public Integer getType ( ) {
        return type;
    }

    public void setType ( Integer type ) {
        this.type = type;
    }

    public Set < Perfil > getProfile ( ) {
        return profiles.stream( )
                .map( x -> Perfil.toEnum( x ) )
                .collect( Collectors.toSet( ) );
    }

    public void addPerfi ( Perfil perfil ) {
        profiles.add( perfil.getCod( ) );
    }

    public boolean getSituation ( ) {
        return situation;
    }

    public void setSituation ( boolean situation ) {
        this.situation = situation;
    }

    public boolean equals ( Object object ) {
        if( this == object ) return true;
        if( !(object instanceof Users) ) return false;
        if( !super.equals( object ) ) return false;
        Users users = ( Users ) object;
        return idUsers.equals( users.idUsers );
    }

    public int hashCode ( ) {
        return Objects.hash( super.hashCode( ) , idUsers );
    }

    @java.lang.Override
    public java.lang.String toString ( ) {
        return "Users{"
               + "idUsers="
               + idUsers
               + ", name='"
               + name
               + '\''
               + ", password='"
               + password
               + '\''
               + ", email='"
               + email
               + '\''
               + ", type="
               + type
               + ", situation="
               + situation
               + '}';
    }
}