package com.simpletech.webanalytics.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.simpletech.webanalytics.model.Action;
import com.simpletech.webanalytics.dao.base.BaseDaoMybatisMYSQLImpl.MybatisMultiDao;


/**
 * 数据库表t_action的mapper接口
 * @author 树朾
 * @date 2015-12-11 18:11:54 中国标准时间
 */
public interface ActionMapper extends MybatisMultiDao<Action>{

	/**
	 * 插入一条新数据
	 * @param model 添加的数据
	 * @return 改变的行数
	 */
	@Insert("INSERT INTO t_action ( id , idsite , idsubsite , create_time , update_time , idvisitor , idvisit , idtitle , idurl , server_time , time_loaded , time_spent , ref_id_url , ref_id_title , ref_time_spent ) VALUES ( #{id} , #{idsite} , #{idsubsite} , #{createTime} , #{updateTime} , #{idvisitor} , #{idvisit} , #{idtitle} , #{idurl} , #{serverTime} , #{timeLoaded} , #{timeSpent} , #{refIdUrl} , #{refIdTitle} , #{refTimeSpent} )")
	int insert(Action model);
	/**
	 * 根据ID删除
	 * @param id 数据的主键ID
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM t_action WHERE id=#{id}")
	int delete(@Param("id") Object id);
	/**
	 * 更新一条数据
	 * @param model 更新的数据
	 * @return 改变的行数
	 */
	@Update("UPDATE t_action SET id=#{id} , idsite=#{idsite} , idsubsite=#{idsubsite} , create_time=#{createTime} , update_time=#{updateTime} , idvisitor=#{idvisitor} , idvisit=#{idvisit} , idtitle=#{idtitle} , idurl=#{idurl} , server_time=#{serverTime} , time_loaded=#{timeLoaded} , time_spent=#{timeSpent} , ref_id_url=#{refIdUrl} , ref_id_title=#{refIdTitle} , ref_time_spent=#{refTimeSpent} WHERE id=#{id} ")
	int update(Action model);
	/**
	 * 统计全部出数据
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM t_action")
	int countAll();
	/**
	 * 根据ID获取
	 * @param id 主键ID
	 * @return null 或者 主键等于id的数据
	 */
	@Select("SELECT id , idsite , idsubsite , create_time createTime , update_time updateTime , idvisitor , idvisit , idtitle , idurl , server_time serverTime , time_loaded timeLoaded , time_spent timeSpent , ref_id_url refIdUrl , ref_id_title refIdTitle , ref_time_spent refTimeSpent FROM t_action WHERE id=#{id}")
	Action findById(@Param("id") Object id);
	/**
	 * 获取全部数据
	 * @return 全部数据列表
	 */
	@Select("SELECT id , idsite , idsubsite , create_time createTime , update_time updateTime , idvisitor , idvisit , idtitle , idurl , server_time serverTime , time_loaded timeLoaded , time_spent timeSpent , ref_id_url refIdUrl , ref_id_title refIdTitle , ref_time_spent refTimeSpent FROM t_action ${order}")
	List<Action> findAll(@Param("order") String order);
	/**
	 * 分页查询数据
	 * @param limit 最大返回
	 * @param start 起始返回
	 * @return 分页列表数据
	 */
	@Select("SELECT id , idsite , idsubsite , create_time createTime , update_time updateTime , idvisitor , idvisit , idtitle , idurl , server_time serverTime , time_loaded timeLoaded , time_spent timeSpent , ref_id_url refIdUrl , ref_id_title refIdTitle , ref_time_spent refTimeSpent FROM t_action ${order} LIMIT ${start},${limit}")
	List<Action> findByPage(@Param("order") String order,@Param("limit") int limit,@Param("start") int start);
	/**
	 * 选择性删除
	 * @param where SQL条件语句
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM t_action ${where}")
	int deleteWhere(@Param("where") String where);
	/**
	 * 根据属性值删除
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 改变的行数
	 */
	@Delete("DELETE FROM t_action WHERE ${propertyName}=#{value}")
	int deleteByPropertyName(@Param("propertyName") String propertyName,@Param("value") Object value);
	/**
	 * 选择性统计
	 * @param where SQL条件语句
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM t_action ${where}")
	int countWhere(@Param("where") String where);
	/**
	 * 根据属性统计
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 统计数
	 */
	@Select("SELECT COUNT(*) FROM WHERE ${propertyName}=#{value}")
	int countByPropertyName(@Param("propertyName") String propertyName,@Param("value") Object value);
	/**
	 * 选择性查询
	 * @param where SQL条件语句
	 * @return 符合条件的列表数据
	 */
	@Select("SELECT id , idsite , idsubsite , create_time createTime , update_time updateTime , idvisitor , idvisit , idtitle , idurl , server_time serverTime , time_loaded timeLoaded , time_spent timeSpent , ref_id_url refIdUrl , ref_id_title refIdTitle , ref_time_spent refTimeSpent FROM t_action ${where} ${order}")
	List<Action> findWhere(@Param("order") String order,@Param("where") String where);
	/**
	 * 选择性分页查询
	 * @param where SQL条件语句
	 * @param limit 最大返回
	 * @param start 起始返回
	 * @return 符合条件的列表数据
	 */
	@Select("SELECT id , idsite , idsubsite , create_time createTime , update_time updateTime , idvisitor , idvisit , idtitle , idurl , server_time serverTime , time_loaded timeLoaded , time_spent timeSpent , ref_id_url refIdUrl , ref_id_title refIdTitle , ref_time_spent refTimeSpent FROM t_action ${where} ${order} LIMIT ${start},${limit}")
	List<Action> findWhereByPage(@Param("order") String order,@Param("where") String where,@Param("limit") int limit,@Param("start") int start);
	/**
	 * 根据属性查询
	 * @param propertyName 数据库列名
	 * @param value 值
	 * @return 返回符合条件的数据列表
	 */
	@Select("SELECT id , idsite , idsubsite , create_time createTime , update_time updateTime , idvisitor , idvisit , idtitle , idurl , server_time serverTime , time_loaded timeLoaded , time_spent timeSpent , ref_id_url refIdUrl , ref_id_title refIdTitle , ref_time_spent refTimeSpent FROM t_action WHERE ${propertyName}=#{value} ${order}")
	List<Action> findByPropertyName(@Param("order") String order,@Param("propertyName") String propertyName,@Param("value") Object value);
}