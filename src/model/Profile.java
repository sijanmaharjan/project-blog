package model;

import javax.persistence.*;

@Entity
@Table
public class Profile implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
