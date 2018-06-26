package top.guaiguo.daoconf;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description
 * @Datetime 2018-06-25 18:45
 */
@Configuration
@MapperScan(basePackages = MysqlReadConfig.BASEPACKAGES)
public class MysqlReadConfig {

    static final String MAPPER_LOCATIONS = "classpath:mappers/*.xml";
    static final String BASEPACKAGES = "top.guaiguo.dao";

    @Bean(name = "mysqlDruidDataSource")
    @Primary
    @ConfigurationProperties(prefix = "mysql.read")
    public DataSource setDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "mysqlDruidSqlSessionFactory")
    @Primary
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("mysqlDruidDataSource") DataSource mysqlDruidDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATIONS);
        bean.setMapperLocations(resources);
        bean.setDataSource(mysqlDruidDataSource);
        return bean.getObject();
    }

    @Bean("mysqlTransactionManager")
    @Primary
    public DataSourceTransactionManager setTransactionManager(@Qualifier("mysqlDruidDataSource") DataSource
            dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
