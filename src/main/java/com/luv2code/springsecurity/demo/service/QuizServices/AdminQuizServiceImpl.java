package com.luv2code.springsecurity.demo.service.QuizServices;

import com.luv2code.springsecurity.demo.dao.Quiz.AdminDAOImp;
import com.luv2code.springsecurity.demo.entity.QuizEntity.QuizDB;
import com.luv2code.springsecurity.demo.entity.Users;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminQuizServiceImpl implements AdminQuizService {

    @Autowired
    private AdminDAOImp adminDAOImp;

    public String addQuestion(QuizDB quizDB) {

       try {
            adminDAOImp.persisQuestion(quizDB);
            return  "true";

        } catch (Exception var12) {
            var12.printStackTrace();
            return "false";
        }
    }

    public  QuizDB getQuestions() {

        try {
            QuizDB question = adminDAOImp.findQuestion();
            return  question;
        }  catch (Exception var8) {
            var8.printStackTrace();
            return  null;
        }

    }

    @Override
    public QuizDB getQuestionsByQuestionId(int Id) {
        try {
            QuizDB question = adminDAOImp.findQuestionById(Id);

            return  question;
        }  catch (Exception var8) {
            var8.printStackTrace();
            return  null;
        }
    }

    @Override
    public Users getUserByUsername(String username) {
        try {
            Users user= adminDAOImp.findUserByUsername(username);
            return  user;
        }  catch (Exception var8) {
            var8.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateScore(Users user) {
        adminDAOImp.updateUser(user);
    }















   /* public  String login(String email, String password) {

        String query = "select * from " + DBDetails.ADMIN_TABLE + " where " + DBDetails.EMAIL_COL + "=? and " + DBDetails.PASSWORD_COL + "=?";

        try {
            Connection con = DBConnectImp.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return "true";
            }

            ps.close();
            con.close();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return "false";
    }


    public  JSONArray getResult() {
        String query = "select * from " + DBDetails.USER_TABLE;
        JSONArray array = new JSONArray();

        try {
            Connection con = DBConnectImp.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ResultSet rs1 = null;
            query = "select * from " + DBDetails.RESULT_TABLE + " where " + DBDetails.USER_ID_COL + "=?";

            while(rs.next()) {
                ps = con.prepareStatement(query);
                ps.setString(1, rs.getString(DBDetails.ID_COL));
                rs1 = ps.executeQuery();
                int points = 0;

                while(rs1.next()) {
                    if (checkAnswer(rs1.getString(DBDetails.QUESTION_ID_COL), rs1.getString(DBDetails.ANSWER_COL))) {
                        ++points;
                    }
                }

                JSONObject obj = new JSONObject();
                obj.put("name", rs.getString(DBDetails.NAME_COL));
                obj.put("email", rs.getString(DBDetails.EMAIL_COL));
                obj.put("points", points);
                array.add(obj);
            }

            rs1.close();
            rs.close();
            ps.close();
            con.close();
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        JSONArray sortedJsonArray = new JSONArray();
        ArrayList<JSONObject> jsonValues = new ArrayList();

        int i;
        for(i = 0; i < array.size(); ++i) {
            jsonValues.add((JSONObject)array.get(i));
        }

        Collections.sort(jsonValues, new Comparator<JSONObject>() {
            private static final String KEY_NAME = "points";

            public int compare(JSONObject a, JSONObject b) {
                Integer valA = null;
                Integer valB = null;

                try {
                    valA = new Integer(String.valueOf(a.get("points")));
                    valB = new Integer(String.valueOf(b.get("points")));
                } catch (Exception var6) {
                    var6.printStackTrace();
                }

                return valB.intValue() > valA.intValue() ? 1 : (valB.intValue() < valA.intValue() ? -1 : 0);
            }
        });

        for(i = 0; i < array.size(); ++i) {
            sortedJsonArray.add(jsonValues.get(i));
        }

        return sortedJsonArray;
    }

    public boolean checkAnswer(String id, String answer) {
        String query = "select * from " + DBDetails.QUESTION_TABLE + " where " + DBDetails.ID_COL + "=?";

        try {
            Connection con = DBConnectImp.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getString(DBDetails.ANSWER_COL).equals(answer)) {
                return true;
            }

            rs.close();
            ps.close();
            con.close();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return false;
    }

    public  JSONObject getQuestion(String id) {
        String query = "select * from " + DBDetails.QUESTION_TABLE + " where " + DBDetails.ID_COL + "=?";
        JSONObject obj = null;

        try {
            Connection con = DBConnectImp.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            ResultSet rs1 = null;
            if (rs.next()) {
                obj = new JSONObject();
                obj.put("id", rs.getString(DBDetails.ID_COL));
                obj.put("question", rs.getString(DBDetails.TEXT_COL));
                obj.put("answer", rs.getString(DBDetails.ANSWER_COL));
                obj.put("set", rs.getString(DBDetails.SET_COL));
                query = "select * from " + DBDetails.CHOICE_TABLE + " where " + DBDetails.QUESTION_ID_COL + "=?";
                ps = con.prepareStatement(query);
                ps.setString(1, rs.getString(DBDetails.ID_COL));
                rs1 = ps.executeQuery();
                rs1.absolute(1);
                obj.put("a", rs1.getString(DBDetails.TEXT_COL));
                rs1.absolute(2);
                obj.put("b", rs1.getString(DBDetails.TEXT_COL));
                rs1.absolute(3);
                obj.put("c", rs1.getString(DBDetails.TEXT_COL));
                rs1.absolute(4);
                obj.put("d", rs1.getString(DBDetails.TEXT_COL));
            }

            rs.close();
            rs1.close();
            ps.close();
            con.close();
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return obj;
    }

    public  String deleteQuestion(String id) {
        String query = "delete from " + DBDetails.QUESTION_TABLE + " where " + DBDetails.ID_COL + "=?";
        int result1 =0,result2=0;

        try {
            Connection con = DBConnectImp.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, id);
            result1 = ps.executeUpdate();
            query = "delete from " + DBDetails.CHOICE_TABLE + " where " + DBDetails.QUESTION_ID_COL + "=?";
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            result2 = ps.executeUpdate();
            if (result1 > 0 && result2 > 0) {
                return "true";
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return "false";
    }

    public  boolean checkAnswerExists(String id, LinkedHashMap lhm) {
        return lhm.containsKey(id) && lhm.get(id) != null;
    }*/
}
