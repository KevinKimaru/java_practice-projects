package com.kevin.databases;

/**
 * Created by Kevin Kimaru Chege on 8/4/2017.
 */
public class Contact {
    String mFirstName;
    String mLastName;
    String mEmail;
    long mPhone;

    public Contact(String firstName, String lastName, String email, long phone) {
        mFirstName = firstName;
        mLastName = lastName;
        mEmail = email;
        mPhone = phone;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public long getPhone() {
        return mPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (mPhone != contact.mPhone) return false;
        if (mFirstName != null ? !mFirstName.equals(contact.mFirstName) : contact.mFirstName != null) return false;
        if (mLastName != null ? !mLastName.equals(contact.mLastName) : contact.mLastName != null) return false;
        return mEmail != null ? mEmail.equals(contact.mEmail) : contact.mEmail == null;
    }

    @Override
    public int hashCode() {
        int result = mFirstName != null ? mFirstName.hashCode() : 0;
        result = 31 * result + (mLastName != null ? mLastName.hashCode() : 0);
        result = 31 * result + (mEmail != null ? mEmail.hashCode() : 0);
        result = 31 * result + (int) (mPhone ^ (mPhone >>> 32));
        return result;
    }
}
