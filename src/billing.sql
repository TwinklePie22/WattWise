-- Create and use the billing database
CREATE DATABASE IF NOT EXISTS billing;
USE billing;

-- Create the Signup table
CREATE TABLE IF NOT EXISTS Signup (
                                      meter_no VARCHAR(20),
    username VARCHAR(20),
    name VARCHAR(30),
    password VARCHAR(30),
    usertype VARCHAR(20)
    );

-- Display the contents of the Signup table
SELECT * FROM Signup;

-- Create the new_customer table
CREATE TABLE IF NOT EXISTS new_customer (
                                            name VARCHAR(30),
    meter_no VARCHAR(20),
    address VARCHAR(50),
    city VARCHAR(30),
    state VARCHAR(30),
    email VARCHAR(30),
    phone_no VARCHAR(12)
    );

-- Display the contents of the new_customer table
SELECT * FROM new_customer;

-- Create the meter_info table
CREATE TABLE IF NOT EXISTS meter_info (
                                          meter_number VARCHAR(30),
    meter_location VARCHAR(30),
    meter_type VARCHAR(30),
    phase_code VARCHAR(30),
    bill_type VARCHAR(30),
    days VARCHAR(10)
    );

-- Display the contents of the meter_info table
SELECT * FROM meter_info;

-- Create the tax table
CREATE TABLE IF NOT EXISTS tax (
                                   cost_per_unit VARCHAR(20),
    meter_rent VARCHAR(20),
    service_charge VARCHAR(20),
    service_tax VARCHAR(20),
    swacch_bharat VARCHAR(20),
    fixed_tax VARCHAR(20)
    );

-- Insert values into the tax table
INSERT INTO tax VALUES ('10', '45', '20', '58', '5', '18');

-- Display the contents of the tax table
SELECT * FROM tax;

-- Create the bill table
CREATE TABLE IF NOT EXISTS bill (
                                    meter_no VARCHAR(20),
    month VARCHAR(20),
    unit VARCHAR(20),
    total_bill VARCHAR(20),
    status VARCHAR(20)
    );

-- Display the contents of the bill table
SELECT * FROM bill;
