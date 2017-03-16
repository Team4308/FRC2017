package org.usfirst.frc.team4308;

import java.nio.ByteBuffer;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.Set;

import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

public class TableWorker {

	protected String tableName;

	protected NetworkTable table;
	protected Hashtable<Sendable, String> tableData;

	public TableWorker(String tableName) {
		this.tableName = tableName;
		table = NetworkTable.getTable(tableName);
		tableData = new Hashtable<Sendable, String>();
	}

	public void putData(String key, Sendable data) {
		ITable dataTable = table.getSubTable(key);
		dataTable.putString("~TYPE~", data.getSmartDashboardType());
		data.initTable(dataTable);
		tableData.put(data, key);
	}

	public void putData(NamedSendable value) {
		putData(value.getName(), value);
	}

	public Sendable getData(String key) {
		ITable subtable = table.getSubTable(key);
		Object data = tableData.get(subtable);
		if (data == null) {
			throw new IllegalArgumentException(tableName + " data does not exist: " + key);
		} else {
			return (Sendable) data;
		}
	}

	public boolean containsKey(String key) {
		return table.containsKey(key);
	}

	public Set<String> getKeys(int types) {
		return table.getKeys(types);
	}

	public Set<String> getKeys() {
		return table.getKeys();
	}

	public void setPersistent(String key) {
		table.setPersistent(key);
	}

	public void clearPersistent(String key) {
		table.clearPersistent(key);
	}

	public boolean isPersistent(String key) {
		return table.isPersistent(key);
	}

	public void setFlags(String key, int flags) {
		table.setFlags(key, flags);
	}

	public void clearFlags(String key, int flags) {
		table.clearFlags(key, flags);
	}

	public int getFlags(String key) {
		return table.getFlags(key);
	}

	public void delete(String key) {
		table.delete(key);
	}

	public boolean putBoolean(String key, boolean value) {
		return table.putBoolean(key, value);
	}

	public boolean setDefaultBoolean(String key, boolean defaultValue) {
		return table.setDefaultBoolean(key, defaultValue);
	}

	@Deprecated
	public boolean getBoolean(String key) throws TableKeyNotDefinedException {
		return table.getBoolean(key);
	}

	public boolean getBoolean(String key, boolean defaultValue) {
		return table.getBoolean(key, defaultValue);
	}

	public boolean putNumber(String key, double value) {
		return table.putNumber(key, value);
	}

	public boolean setDefaultNumber(String key, double defaultValue) {
		return table.setDefaultNumber(key, defaultValue);
	}

	@Deprecated
	public double getNumber(String key) throws TableKeyNotDefinedException {
		return table.getNumber(key);
	}

	public double getNumber(String key, double defaultValue) {
		return table.getNumber(key, defaultValue);
	}

	public boolean putString(String key, String value) {
		return table.putString(key, value);
	}

	public boolean setDefaultString(String key, String defaultValue) {
		return table.setDefaultString(key, defaultValue);
	}

	@Deprecated
	public String getString(String key) throws TableKeyNotDefinedException {
		return table.getString(key);
	}

	public String getString(String key, String defaultValue) {
		return table.getString(key, defaultValue);
	}

	public boolean putBooleanArray(String key, boolean[] value) {
		return table.putBooleanArray(key, value);
	}

	public boolean putBooleanArray(String key, Boolean[] value) {
		return table.putBooleanArray(key, value);
	}

	public boolean setDefaultBooleanArray(String key, boolean[] defaultValue) {
		return table.setDefaultBooleanArray(key, defaultValue);
	}

	public boolean setDefaultBooleanArray(String key, Boolean[] defaultValue) {
		return table.setDefaultBooleanArray(key, defaultValue);
	}

	@Deprecated
	public boolean[] getBooleanArray(String key) throws TableKeyNotDefinedException {
		return table.getBooleanArray(key);
	}

	public boolean[] getBooleanArray(String key, boolean[] defaultValue) {
		return table.getBooleanArray(key, defaultValue);
	}

	public Boolean[] getBooleanArray(String key, Boolean[] defaultValue) {
		return table.getBooleanArray(key, defaultValue);
	}

	public boolean putNumberArray(String key, double[] value) {
		return table.putNumberArray(key, value);
	}

	public boolean putNumberArray(String key, Double[] value) {
		return table.putNumberArray(key, value);
	}

	public boolean setDefaultNumberArray(String key, double[] defaultValue) {
		return table.setDefaultNumberArray(key, defaultValue);
	}

	public boolean setDefaultNumberArray(String key, Double[] defaultValue) {
		return table.setDefaultNumberArray(key, defaultValue);
	}

	@Deprecated
	public double[] getNumberArray(String key) throws TableKeyNotDefinedException {
		return table.getNumberArray(key);
	}

	public double[] getNumberArray(String key, double[] defaultValue) {
		return table.getNumberArray(key, defaultValue);
	}

	public Double[] getNumberArray(String key, Double[] defaultValue) {
		return table.getNumberArray(key, defaultValue);
	}

	public boolean putStringArray(String key, String[] value) {
		return table.putStringArray(key, value);
	}

	public boolean setDefaultStringArray(String key, String[] defaultValue) {
		return table.setDefaultStringArray(key, defaultValue);
	}

	@Deprecated
	public String[] getStringArray(String key) throws TableKeyNotDefinedException {
		return table.getStringArray(key);
	}

	public String[] getStringArray(String key, String[] defaultValue) {
		return table.getStringArray(key, defaultValue);
	}

	public boolean putRaw(String key, byte[] value) {
		return table.putRaw(key, value);
	}

	public boolean putRaw(String key, ByteBuffer value, int len) {
		return table.putRaw(key, value, len);
	}

	public boolean setDefaultRaw(String key, byte[] defaultValue) {
		return table.setDefaultRaw(key, defaultValue);
	}

	@Deprecated
	public byte[] getRaw(String key) throws TableKeyNotDefinedException {
		return table.getRaw(key);
	}

	public byte[] getRaw(String key, byte[] defaultValue) {
		return table.getRaw(key, defaultValue);
	}

	@Deprecated
	public void putInt(String key, int value) {
		table.putNumber(key, (double) value);
	}

	@Deprecated
	public int getInt(String key) throws TableKeyNotDefinedException {
		return (int) table.getNumber(key);
	}

	@Deprecated
	public int getInt(String key, int defaultValue) throws TableKeyNotDefinedException {
		try {
			return (int) table.getNumber(key);
		} catch (NoSuchElementException arg2) {
			return defaultValue;
		}
	}

	@Deprecated
	public void putDouble(String key, double value) {
		table.putNumber(key, value);
	}

	@Deprecated
	public double getDouble(String key) throws TableKeyNotDefinedException {
		return table.getNumber(key);
	}

	@Deprecated
	public double getDouble(String key, double defaultValue) {
		return table.getNumber(key, defaultValue);
	}

}
