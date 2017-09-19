package com.wyy.dao;

import com.wyy.model.Question;
import com.wyy.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Create by wyy on 2017/8/15.
 */
@Mapper
public interface QuestionDAO {
    String TABLE_NAME = " question ";
    String INSERT_FIELDS = " title,content,created_date,user_id,comment_count ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into", TABLE_NAME, "(", INSERT_FIELDS, ") values(#{title},#{content},#{createdDate},#{userId},#{commentCount})"})
    int addQuestion(Question question);

    List<Question> selectLatestQuestions(@Param("userId") int userId,
                               @Param("offset") int offset,
                               @Param("limit") int limit);

    //@Update({"update ", TABLE_NAME, " set password=#{password} where id=#{id}"})
    //void updatePassword(User user);

    //@Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    //void deleteById(int id) ;

}
