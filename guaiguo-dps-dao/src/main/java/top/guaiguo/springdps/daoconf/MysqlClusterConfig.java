package top.guaiguo.springdps.daoconf;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@MapperScan(basePackages = MysqlMasterConfig.BASEPACKAGES, sqlSessionFactoryRef = "clusterDruidSqlSessionFactory")
public class MysqlClusterConfig {

    @Bean(name = "clusterDruidDataSource")
    @ConfigurationProperties(prefix = "cluster.mysql")
    public DataSource setDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "clusterDruidSqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("clusterDruidDataSource") DataSource mysqlDruidDataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(MysqlMasterConfig
                .MAPPER_LOCATIONS);
        bean.setMapperLocations(resources);
        bean.setDataSource(mysqlDruidDataSource);
        return bean.getObject();
    }

    @Bean("clusterTransactionManager")
    public DataSourceTransactionManager setTransactionManager(@Qualifier("clusterDruidDataSource") DataSource
            dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
