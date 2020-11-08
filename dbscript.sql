DROP DATABASE IF EXISTS loanmanagement;

CREATE DATABASE loanmanagement;

USE loanmanagement;

Create table login(
username varchar(15) primary key,
password varchar(15),
role_id int not null,
role_desc varchar (15)
);

Create table Loan_Information (
application_number int(10) auto_increment primary key,
username varchar(15),
loan_purpose varchar(20) not null,
loan_amt_requested int(10),
loan_applied_date varchar(10),
business_structure varchar(15),
tax_indicator varchar(2),
address_line1 varchar(20),
address_line2 varchar(20),
city varchar(10),
state varchar(10),
postal_code varchar(10),
phone_number varchar(10) not null,
email_id varchar(20),
constraint USER_KEY Foreign Key (username) references login (username)
);

create table Admin_Data (
application_number int(10) primary key,
loan_amount_sanctioned int(10),
loan_duration int(5),
payment_start_date varchar(20),
loan_closure_date varchar(20),
monthly_payment int (10),
loan_status_indicator varchar(2)
);

ALTER TABLE Admin_Data ADD FOREIGN KEY (application_number) REFERENCES Loan_Information (application_number);

create table Reject_Loan_Information (
application_number int(10) primary key,
rejection_reason varchar (15),
loan_amt_requested decimal (10,2),
loan_applied_date date,
loan_rejected_date date
);

ALTER TABLE Reject_Loan_Information ADD FOREIGN KEY (application_number) REFERENCES Loan_Information (application_number);

	