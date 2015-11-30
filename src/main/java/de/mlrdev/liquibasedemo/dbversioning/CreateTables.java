package de.mlrdev.liquibasedemo.dbversioning;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import liquibase.change.custom.CustomSqlChange;
import liquibase.database.Database;
import liquibase.database.jvm.JdbcConnection;
import liquibase.datatype.LiquibaseDataType;
import liquibase.datatype.core.BigIntType;
import liquibase.datatype.core.IntType;
import liquibase.datatype.core.VarcharType;
import liquibase.exception.CustomChangeException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;
import liquibase.statement.SqlStatement;
import liquibase.statement.core.CreateTableStatement;


/**
 * A simple show-case {@link CustomSqlChange} to create tables.
 */
public class CreateTables implements CustomSqlChange
{
    @Override
    public SqlStatement[] generateStatements(final Database database) throws CustomChangeException
    {
        JdbcConnection jdbcConnection = (JdbcConnection) database.getConnection();
        Connection connection = jdbcConnection.getUnderlyingConnection();

        List<SqlStatement> stmts = new ArrayList<>();
        stmts.addAll(createTable(connection, "foo",
            new SimpleColumn("id", new IntType()),
            new SimpleColumn("name", new VarcharType()),
            new SimpleColumn("description", new VarcharType())
        ));

        stmts.addAll(createTable(connection, "bar",
            new SimpleColumn("id", new IntType()),
            new SimpleColumn("name", new VarcharType()),
            new SimpleColumn("amount", new BigIntType())
        ));

        stmts.addAll(createTable(connection, "baz",
            new SimpleColumn("id", new IntType()),
            new SimpleColumn("barid", new IntType()),
            new SimpleColumn("name", new VarcharType()),
            new SimpleColumn("amount", new BigIntType()),
            new SimpleColumn("description", new VarcharType())
        ));

        return stmts.toArray(new SqlStatement[stmts.size()]);
    }

    private Collection<? extends SqlStatement> createTable(final Connection connection,
                                                           final String tableName,
                                                           final SimpleColumn... columns)
        throws CustomChangeException
    {
        List<CreateTableStatement> statements = new ArrayList<>();

        CreateTableStatement createStatement = new CreateTableStatement(null, null, tableName);
        for (SimpleColumn column : columns)
        {
            createStatement.addColumn(column.name, column.type);
        }

        statements.add(createStatement);

        return statements;
    }

    @Override
    public String getConfirmationMessage()
    {
        return null;
    }

    @Override
    public void setUp() throws SetupException
    {

    }

    @Override
    public void setFileOpener(final ResourceAccessor resourceAccessor)
    {

    }

    @Override
    public ValidationErrors validate(final Database database)
    {
        return null;
    }

    /**
     * A simple representation of a column.
     */
    private class SimpleColumn
    {
        private final String name;
        private final LiquibaseDataType type;

        public SimpleColumn(String name, LiquibaseDataType type)
        {
            this.name = name;
            this.type = type;
        }
    }
}
