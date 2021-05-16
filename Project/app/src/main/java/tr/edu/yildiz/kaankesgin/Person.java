package tr.edu.yildiz.kaankesgin;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

public class Person implements Parcelable {
    private String name;
    private String surname;
    private String email;
    private int phoneNumber;
    private String birthDate;
    private String passw;
    private int photo;

    public Person(String name, String surname, String email, int phoneNumber, String birthDate, String passw, int photo) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.passw = passw;
        this.photo = photo;
    }

    public Person(String name, String email, String passw, int photo) {
        this.name = name;
        this.email = email;
        this.passw = passw;
        this.photo = photo;
    }

    protected Person(Parcel in) {
        name = in.readString();
        surname = in.readString();
        email = in.readString();
        phoneNumber = in.readInt();
        birthDate = in.readString();
        passw = in.readString();
        photo = in.readInt();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }


    public static ArrayList<Person> getPersonList(){
        ArrayList persons = new ArrayList();
        persons.add(new Person("Kaan","kaank@kaank.com","123",R.drawable.avatar1));
        return persons;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeString(email);
        dest.writeInt(phoneNumber);
        dest.writeString(birthDate);
        dest.writeString(passw);
        dest.writeInt(photo);
    }
}
