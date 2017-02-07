package uk.ac.cardiff.nsa.security.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import uk.ac.cardiff.nsa.security.service.AccountService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by philsmart on 04/02/2017.
 */
@Repository
public class AccountRepository {

    private static final Logger log = LoggerFactory.getLogger(AccountRepository.class);

    @Value("${service.account.find-user.query}")
    private String findUserQuery;

    @Inject
    private DataSource dataSource;


    @PostConstruct
    public void init(){
        Objects.requireNonNull(dataSource);
        Objects.requireNonNull(findUserQuery);


    }

    public List<Map<String,Object>> findUserAccount(final String user) {
        //note, transactions not configured - not so import on read only?!
        try {
            String queryToRun = findUserQuery+"'"+user+"'";
            log.info("Running user query [{}]", queryToRun);
            final Connection conn = dataSource.getConnection();
            final Statement stmt = conn.createStatement();

            final ResultSet results = stmt.executeQuery(queryToRun);
            int cols = results.getMetaData().getColumnCount();



            final List<Map<String,Object>> userResultMap = new ArrayList<Map<String,Object>>();

            while (results.next()){
                final Map<String,Object> result = new HashMap<String,Object>();
                for (int i=1; i <= cols;i++){
                    log.debug("Result {}, {}",results.getMetaData().getColumnName(i),results.getObject(i));
                    result.put(results.getMetaData().getColumnName(i),results.getObject(i));
                }
                userResultMap.add(result);
            }


            stmt.close();
            conn.close();

            return userResultMap;

        } catch (SQLException e) {
            log.error("Could not find user, SQL error", e);
        }
        return Collections.emptyList();
    }
}
