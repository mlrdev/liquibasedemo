package de.mlrdev.liquibasedemo;

import liquibase.change.custom.CustomSqlChange;
import liquibase.database.Database;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.CustomChangeException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;
import liquibase.statement.SqlStatement;
import liquibase.statement.core.UpdateStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A simple show-case {@link CustomSqlChange} to alter person entities.
 */
public class AlterPerson implements CustomSqlChange {

    private static final Logger L = LoggerFactory.getLogger(AlterPerson.class);

    @Override
    public SqlStatement[] generateStatements(Database database) throws CustomChangeException {

        JdbcConnection jdbcConnection = (JdbcConnection) database.getConnection();
        Connection connection = jdbcConnection.getUnderlyingConnection();

        List<SqlStatement> statements = new ArrayList<>();
        statements.addAll(createStatements(connection));

        return statements.toArray(new SqlStatement[statements.size()]);
    }

    /**
     * Creates a statement where all entities with odd <code>id's</code> got their birthday set to null.
     *
     * @param connection
     * @return
     * @throws CustomChangeException
     */
    private Collection<? extends SqlStatement> createStatements(Connection connection) throws CustomChangeException {
        List<SqlStatement> statements = new ArrayList<>();

        try (
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM person WHERE (id % 2) > 0")) {

            while (rs.next()) {
                long id = rs.getLong("id");
                UpdateStatement updateStmt = new UpdateStatement(null, null, "person");
                updateStmt.setWhereClause("id = " + id);
                updateStmt.addNewColumnValue("birthday", null);
                statements.add(updateStmt);
            }
        } catch (SQLException e) {
            throw new CustomChangeException(e);
        }

        return statements;
    }

    @Override
    public String getConfirmationMessage() {
        return null;
    }

    @Override
    public void setUp() throws SetupException {

    }

    @Override
    public void setFileOpener(ResourceAccessor resourceAccessor) {

    }

    @Override
    public ValidationErrors validate(Database database) {
        return null;
    }
}
