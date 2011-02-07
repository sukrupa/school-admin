package org.sukrupa.event;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;
import org.jadira.usertype.dateandtime.joda.PersistentDateTime;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersistentEventDate implements UserType, Serializable{

	private PersistentDateTime delegate = new PersistentDateTime();

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		return delegate.equals(dateTimeFor(x), dateTimeFor(y));
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return delegate.hashCode(x);
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public int[] sqlTypes() {
		return delegate.sqlTypes();
	}

	@Override
	public Class returnedClass() {
		return EventDate.class;
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

	@Override
	public Object nullSafeGet(ResultSet resultSet, String[] strings, Object object) throws HibernateException, SQLException {
		return dateFor(delegate.nullSafeGet(resultSet, strings, object));
	}

	@Override
	public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index) throws HibernateException, SQLException {
		delegate.nullSafeSet(preparedStatement, dateTimeFor(value), index);
	}

	private DateTime dateTimeFor(Object date) {
		return (date == null) ? null : ((EventDate) date).getJodaDateTime();
	}

	private EventDate dateFor(DateTime dateTime) {
		return (dateTime == null) ? null : new EventDate(dateTime.getMillis());
	}
}
