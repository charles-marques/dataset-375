package com.jaccson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.apache.accumulo.core.client.AccumuloException;
import org.apache.accumulo.core.client.AccumuloSecurityException;
import org.apache.accumulo.core.client.IteratorSetting;
import org.apache.accumulo.core.client.MutationsRejectedException;
import org.apache.accumulo.core.client.TableExistsException;
import org.apache.accumulo.core.client.TableNotFoundException;
import org.apache.accumulo.core.iterators.Combiner;
import org.apache.accumulo.core.iterators.IteratorUtil;

import com.mongodb.CommandResult;
import com.mongodb.DBEncoder;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;

public class DB {

	String name;
	Jaccson jaccson;
	DatabasesTable dbsTable;

	DB(String name, Jaccson jaccson) throws MutationsRejectedException, IOException {
		this.name = name;
		this.jaccson = jaccson;

		dbsTable = new DatabasesTable(jaccson);

		if(!dbsTable.existsDB(name)) {
			dbsTable.addDatabase(name);
		}

	}

	public DBCollection createCollection(String coll, DBObject options) throws TableNotFoundException, AccumuloException, AccumuloSecurityException {

		if(!dbsTable.existsCollection(name, coll)) {
			try {
				String tableName = name + "_" + coll;
				jaccson.conn.tableOperations().create(tableName);

				// remove default versioning iterator
				jaccson.conn.tableOperations().removeIterator(tableName, "vers", EnumSet.allOf(IteratorUtil.IteratorScope.class));

				// apply update iterator
				IteratorSetting upiterset = new IteratorSetting(IterStack.UPDATER_ITERATOR_PRI, "jaccsonUpdater", "com.jaccson.server.JaccsonUpdater");

				ArrayList<IteratorSetting.Column> cols = new ArrayList<IteratorSetting.Column>();
				cols.add(new IteratorSetting.Column("BSON"));
				Combiner.setColumns(upiterset, cols);
				jaccson.conn.tableOperations().attachIterator(tableName, upiterset);

				// apply deleted filter
				IteratorSetting deliterset = new IteratorSetting(IterStack.DELETED_ITERATOR_PRI, "jaccsonDeleter", "com.jaccson.server.DeletedFilter");
				jaccson.conn.tableOperations().attachIterator(tableName, deliterset);

				dbsTable.addCollection(name, coll);

				// TODO: need to throw any of these?
			} catch (AccumuloException e) {
				e.printStackTrace();
			} catch (AccumuloSecurityException e) {
				e.printStackTrace();
			} catch (TableExistsException e) {
				e.printStackTrace();
			} catch (TableNotFoundException e) {
				e.printStackTrace();
			}
		}

		return new DBCollection(coll, this);
	}

	public CommandResult command(DBObject cmd) throws MongoException {
		// TODO Auto-generated method stub
		return null;
	}


	public CommandResult command(DBObject cmd, DBEncoder encoder)
			throws MongoException {
		// TODO Auto-generated method stub
		return null;
	}


	public CommandResult command(DBObject cmd, int options, DBEncoder encoder)
			throws MongoException {
		// TODO Auto-generated method stub
		return null;
	}


	public CommandResult command(DBObject cmd, int options,
			ReadPreference readPrefs) throws MongoException {
		// TODO Auto-generated method stub
		return null;
	}


	public CommandResult command(DBObject cmd, int options,
			ReadPreference readPrefs, DBEncoder encoder) throws MongoException {
		// TODO Auto-generated method stub
		return null;
	}


	public CommandResult command(DBObject cmd, int options)
			throws MongoException {
		// TODO Auto-generated method stub
		return null;
	}


	public CommandResult command(String cmd) throws MongoException {
		// TODO Auto-generated method stub
		return null;
	}


	public CommandResult command(String cmd, int options) throws MongoException {
		// TODO Auto-generated method stub
		return null;
	}


	public CommandResult doEval(String code, Object... args)
			throws MongoException {
		// TODO Auto-generated method stub
		return null;
	}


	public CommandResult getStats() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getName() {
		return name;
	}


	public boolean collectionExists(String collectionName) {

		return dbsTable.existsCollection(name, collectionName);
	}


	public CommandResult getLastError() throws MongoException {
		// TODO Auto-generated method stub
		return null;
	}


	public CommandResult getLastError(WriteConcern concern)
			throws MongoException {
		// TODO Auto-generated method stub
		return null;
	}


	public CommandResult getLastError(int w, int wtimeout, boolean fsync)
			throws MongoException {
		// TODO Auto-generated method stub
		return null;
	}


	public WriteConcern getWriteConcern() {
		// TODO Auto-generated method stub
		return null;
	}


	public ReadPreference getReadPreference() {
		// TODO Auto-generated method stub
		return null;
	}


	public void dropDatabase() throws MongoException {

		try {
			// delete all collections
			for(String collname : dbsTable.listCollections(name)) {

				DBCollection coll = new DBCollection(collname, this);
				// this calls removeCollection()
				coll.drop();
			}

			dbsTable.removeDatabase(name);

		} catch (Exception e) {
			throw new MongoException(e.getLocalizedMessage());
		} 
	}


	public boolean authenticate(String username, char[] passwd)
			throws MongoException {
		// TODO Auto-generated method stub
		return false;
	}


	public CommandResult authenticateCommand(String username, char[] passwd)
			throws MongoException {
		// TODO Auto-generated method stub
		return null;
	}


	public WriteResult addUser(String username, char[] passwd) {
		// TODO Auto-generated method stub
		return null;
	}


	public WriteResult addUser(String username, char[] passwd, boolean readOnly) {
		// TODO Auto-generated method stub
		return null;
	}


	public CommandResult getPreviousError() throws MongoException {
		// TODO Auto-generated method stub
		return null;
	}


	public Jaccson getMongo() {

		return jaccson;
	}


	public DB getSisterDB(String name) {
		// TODO Auto-generated method stub
		return null;
	}


	public void addOption(int option) {
		// TODO Auto-generated method stub

	}


	public int getOptions() {
		// TODO Auto-generated method stub
		return 0;
	}


	public void cleanCursors(boolean force) throws MongoException {
		// TODO Auto-generated method stub

	}


	public DBCollection getCollection(String name) {

		try {
			return new DBCollection(name, this);
		} catch (Exception e) {
			throw new MongoException(e.getLocalizedMessage());
		}
	}


	public DBCollection getCollectionFromString(String s) {
		// TODO Auto-generated method stub
		return null;
	}


	public Object eval(String code, Object... args) throws MongoException {
		// TODO Auto-generated method stub
		return null;
	}


	public Set<String> getCollectionNames() throws MongoException {

		HashSet<String> names = new HashSet<String>();

		names.addAll(dbsTable.listCollections(name));

		return names;
	}


	public void forceError() throws MongoException {
		// TODO Auto-generated method stub

	}


	public void requestStart() {
		// TODO Auto-generated method stub

	}


	public void requestDone() {
		// TODO Auto-generated method stub

	}


	public void requestEnsureConnection() {
		// TODO Auto-generated method stub

	}


	public void setReadOnly(Boolean b) {
		// TODO Auto-generated method stub

	}


	public void setWriteConcern(WriteConcern concern) {
		// TODO Auto-generated method stub

	}


	public void setReadPreference(ReadPreference preference) {
		// TODO Auto-generated method stub

	}


	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return false;
	}


	public WriteResult removeUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}


	public void resetError() throws MongoException {
		// TODO Auto-generated method stub

	}


	public void slaveOk() {
		// TODO Auto-generated method stub

	}


	public void setOptions(int options) {
		// TODO Auto-generated method stub

	}


	public void resetOptions() {
		// TODO Auto-generated method stub

	}

	// called by DBCollection.drop()
	void removeCollection(String coll) throws MutationsRejectedException {
		dbsTable.removeCollection(name, coll);
	}
}
