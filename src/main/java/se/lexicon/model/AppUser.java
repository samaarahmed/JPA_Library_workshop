package se.lexicon.model;

import com.sun.corba.se.pept.transport.ContactInfo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class AppUser {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int appUserId;
        @Column(unique = true)
        private String userName;
        private String password;
        private LocalDate regDate;
        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "fk_details_detailsId")
        private Details userDetails;

        public AppUser(String userName, String password, LocalDate regDate) {
                this.userName = userName;
                this.password = password;
                this.regDate = regDate;
        }

        public AppUser() {
        }

        public int getAppUserId() {
                return appUserId;
        }

        public String getUserName() {
                return userName;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public LocalDate getRegDate() {
                return regDate;
        }

        public void setRegDate(LocalDate regDate) {
                this.regDate = regDate;
        }

        public Details getUserDetails() {
                return userDetails;
        }

        public void setUserDetails(Details userDetails) {
                this.userDetails = userDetails;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                AppUser appUser = (AppUser) o;
                return getAppUserId() == appUser.getAppUserId() && Objects.equals(getUserName(), appUser.getUserName()) && Objects.equals(getPassword(), appUser.getPassword()) && Objects.equals(getRegDate(), appUser.getRegDate()) && Objects.equals(getUserDetails(), appUser.getUserDetails());
        }

        @Override
        public int hashCode() {
                return Objects.hash(getAppUserId(), getUserName(), getPassword(), getRegDate(), getUserDetails());
        }

        @Override
        public String toString() {
                return "AppUser{" +
                        "appUserId=" + appUserId +
                        ", userName='" + userName + '\'' +
                        ", password='" + password + '\'' +
                        ", regDate=" + regDate +
                        ", userDetails=" + userDetails +
                        '}';
        }
}
