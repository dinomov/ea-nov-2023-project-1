package com.ea.entity;

import lombok.Data;
import jakarta.persistence.*;
@Entity
@Data
@Table(name = "staffs")
public class Staff extends User {

    private String title;
}
