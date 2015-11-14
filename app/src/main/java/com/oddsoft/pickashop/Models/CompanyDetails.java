package com.oddsoft.pickashop.Models;

import java.util.ArrayList;

/**
 * Created by afsal on 14/11/15.
 */
public class CompanyDetails {


    /*
    {
    "basic": {
        "company_id": "59",
        "company_name": "Zion Duty Paid Shop",
        "company_type": "Duty Paid Shop and Foreign Exchange",
        "about": "<p>Our shop deals with duty paid items and money exchange and money transfer.</p>",
        "workhour": "9.30 AM  -  8 PM"
    },
    "contact": {
        "mail": "zionettumanoor@gmail.com",
        "phone1": "9447047525",
        "phone2": "0481-2537197",
        "phone3": "",
        "fax1": "",
        "fax2": "",
        "toll1": "",
        "toll2": "",
        "website": ""
    },
    "location": {
        "building": "Parayil Buiding",
        "street": "Ettumanoor Vaikom RD",
        "landmark": "Near Mahadeva Temple",
        "city": "Ettumanoor",
        "pincode": "686631",
        "district": "Kottayam",
        "state": "Kerala",
        "country": "India"
    },
    "media": {
        "banner": "http://www.pickashop.com/mediafiles/comp-A6r-59/basic/banner.jpg",
        "logo": ""
    }
}
     */
    public String company_id;

    public String company_name;

    public String company_type;
    public String about;
    public String workHour;
    public String mail;
    public String phone1;
    public String phone2;
    public String phone3;
    public String fax1;
    public String fax2;
    public String toll1;
    public String toll2;
    public String website;
    public String building;
    public String streat;
    public String landmark;
    public String city;
    public String pincode;
    public String district;
    public String state;
    public String country;
    public ArrayList<String> media;


}
