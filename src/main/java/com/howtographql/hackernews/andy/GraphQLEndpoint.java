package com.howtographql.hackernews.andy;

import com.coxautodev.graphql.tools.SchemaParser;
import com.howtographql.hackernews.andy.mapper.LinkMapper;
import com.howtographql.hackernews.andy.mapper.UserMapper;
import com.howtographql.hackernews.andy.repository.UserRepository;
import com.howtographql.hackernews.andy.repository.UserRepositoryImpl;
import com.howtographql.hackernews.andy.resolver.LinkResolver;
import com.howtographql.hackernews.andy.service.LinkService;
import com.howtographql.hackernews.andy.service.LinkServiceImpl;
import com.howtographql.hackernews.andy.service.UserService;
import com.howtographql.hackernews.andy.service.UserServiceImpl;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionManager;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;


@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

    public GraphQLEndpoint() {
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {
        SqlSessionManager sesionManager= getSqlSessionfactory();
        LinkRepository linkRepository = new LinkRepositoryImpl(sesionManager);
        LinkService linkService = new LinkServiceImpl(linkRepository, sesionManager);

        UserRepository userRepo = new UserRepositoryImpl(sesionManager);
        UserService userService = new UserServiceImpl(userRepo, sesionManager);

        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(
                        new Query(linkService),
                        new Mutation(linkService, userService),
                        new LinkResolver(userRepo))
                .build()
                .makeExecutableSchema();
    }


    private static SqlSessionManager getSqlSessionfactory(){
        DataSource dataSource = getDataSource();
        TransactionFactory transactionFactory =
                new JdbcTransactionFactory();
        Environment environment =
                new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);

        configuration.addMapper(LinkMapper.class);
        configuration.addMapper(UserMapper.class);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(configuration);
        SqlSessionManager sessionManager = SqlSessionManager.newInstance(sqlSessionFactory);
        return sessionManager;
    }


    private static DataSource getDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/hackernews_graphql_java");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }
}