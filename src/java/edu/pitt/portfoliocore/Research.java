package edu.pitt.portfoliocore;


import edu.pitt.utilities.DbUtilities;
import edu.pitt.utilities.ErrorLogger;
import edu.pitt.utilities.StringUtilities;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author mshirey
 */
public class Research {

    private String researchID;
    private String userID;
    private String researchName;
    private String researchAbstract;
    private String journal;
    private String submissionDate;
    private String publishedDate;    
    private String created;
    private String modified;
    private String publishLink;
    

    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private DbUtilities db;

    public Research(String researchID) {
        setAllResearchProperties(StringUtilities.cleanMySqlInsert(researchID));
    }

    public Research(String businessName, String position, String startDate, String endDate, String description) {
        researchID = UUID.randomUUID().toString();
        db = new DbUtilities();
        String sql = "INSERT INTO rms.Research ";

        sql += "(researchID,fk_userID,researchName,researchAbstract,journal,submissionDate,publishedDate,created,modified,publishLink)";
        sql += " VALUES (";
        sql += "'" + this.researchID + "', ";
        sql += "'" + StringUtilities.cleanMySqlInsert(userID) + "', ";
        sql += "'" + StringUtilities.cleanMySqlInsert(researchName) + "', ";
        sql += "'" + StringUtilities.cleanMySqlInsert(journal) + "', ";
        sql += "'" + StringUtilities.cleanMySqlInsert(submissionDate) + "', ";
        sql += "'" + StringUtilities.cleanMySqlInsert(publishedDate) + "', ";   
        sql += "'" + StringUtilities.cleanMySqlInsert(publishLink) + "', ";
        sql += "'" + StringUtilities.cleanMySqlInsert(researchAbstract) + "',NULL,NULL);";
        System.out.println(sql);
        try {
            db.executeQuery(sql);
        } catch (Exception ex) {
            ErrorLogger.log("An error has occurred in with the insert query inside of the Research constructor. " + ex.getMessage());
            ErrorLogger.log(sql);
        } finally {
            setAllResearchProperties(researchID);
            db.closeMySQLConnection();
        }
    }

    /**
     * Creates a Research object from JSON
     *
     * @param Research JSON object for an Education object
     */
    public Research(JSONObject Research) {
        try {
            this.researchID = Research.getString("researchID");
            setResearchFromJSON(Research);
        } catch (JSONException ex) {
            Logger.getLogger(Research.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setAllResearchProperties(String researchID) {
        this.researchID = researchID;
        db = new DbUtilities();
        String sql = "SELECT * FROM rms.Research WHERE researchID = '" + researchID + "'";
        try {
            ResultSet rs = db.getResultSet(sql);
            if (rs.next()) {
                this.researchName = rs.getString("researchName");
                this.userID = rs.getString("fk_userID");
                this.submissionDate = rs.getString("submissionDate");
                this.publishedDate = rs.getString("publishedDate");
                this.journal = rs.getString("journal");
                this.researchAbstract = rs.getString("researchAbstract");
                this.publishLink = rs.getString("publishLink");
                this.created = rs.getTimestamp("created").toString();
                this.modified = rs.getTimestamp("modified").toString();
            }
        } catch (SQLException ex) {
            ErrorLogger.log("An error has occurred in Research(String researchID) constructor of Research class. " + ex.getMessage());
            ErrorLogger.log(sql);
        } finally {
            this.researchID = researchID;
            db.closeMySQLConnection();
        }
    }
    
     public void setUserID(String userID) {
        db = new DbUtilities();
        String sql = "UPDATE Research SET fk_userID = '" + StringUtilities.cleanMySqlInsert(userID) + "' WHERE resumeID = '" + this.researchID + "';";
        try {
            db.executeQuery(sql);
        } catch (Exception ex) {
            ErrorLogger.log("An error has occurred in with the insert query inside of Research's setUserID. " + ex.getMessage());
            ErrorLogger.log(sql);
        } finally {
            this.userID = StringUtilities.cleanMySqlInsert(userID);
            db.closeMySQLConnection();
        }
    }

    public void setResearchName(String researchName) {
        db = new DbUtilities();
        String sql = "UPDATE Research SET researchName = '" + StringUtilities.cleanMySqlInsert(researchName) + "' WHERE researchID = '" + this.researchID + "';";
        try {
            db.executeQuery(sql);
        } catch (Exception ex) {
            ErrorLogger.log("An error has occurred in with the insert query inside of setResearchName. " + ex.getMessage());
            ErrorLogger.log(sql);
        } finally {
            db.closeMySQLConnection();
        }
        this.researchName = StringUtilities.cleanMySqlInsert(researchName);
        setModified();
    }

    public void setJournal(String journal) {
        db = new DbUtilities();
        String sql = "UPDATE Research SET journal = '" + StringUtilities.cleanMySqlInsert(journal) + "' WHERE researchID = '" + this.researchID + "';";
        try {
            db.executeQuery(sql);
        } catch (Exception ex) {
            ErrorLogger.log("An error has occurred in with the insert query inside of setJournal. " + ex.getMessage());
            ErrorLogger.log(sql);
        } finally {
            db.closeMySQLConnection();
        }
        this.journal = StringUtilities.cleanMySqlInsert(journal);
        setModified();
    }

    public void setSubmissionDate(String submissionDate) {
        db = new DbUtilities();
        String sql = "UPDATE Research SET submissionDate = '" + StringUtilities.cleanMySqlInsert(submissionDate) + "' WHERE researchID = '" + this.researchID + "';";
        try {
            db.executeQuery(sql);
        } catch (Exception ex) {
            ErrorLogger.log("An error has occurred in with the insert query inside of setSubmissionDate. " + ex.getMessage());
            ErrorLogger.log(sql);
        } finally {
            db.closeMySQLConnection();
        }
        this.submissionDate = StringUtilities.cleanMySqlInsert(submissionDate);
        setModified();
    }

    public void setPublishedDate(String publishedDate) {
        db = new DbUtilities();
        String sql = "UPDATE Research SET publishedDate = '" + StringUtilities.cleanMySqlInsert(publishedDate) + "' WHERE researchID = '" + this.researchID + "';";
        try {
            db.executeQuery(sql);
        } catch (Exception ex) {
            ErrorLogger.log("An error has occurred in with the insert query inside of setPublishedDate. " + ex.getMessage());
            ErrorLogger.log(sql);
        } finally {
            db.closeMySQLConnection();
        }
        this.publishedDate = StringUtilities.cleanMySqlInsert(publishedDate);
        setModified();
    }

    public void setPublishLink(String publishLink) {
        db = new DbUtilities();
        String sql = "UPDATE Research SET publishLink = " + publishLink + " WHERE researchID = '" + this.researchID + "';";
        try {
            db.executeQuery(sql);
        } catch (Exception ex) {
            ErrorLogger.log("An error has occurred in with the insert query inside of setPublishLink. " + ex.getMessage());
            ErrorLogger.log(sql);
        } finally {
            db.closeMySQLConnection();
        }
        this.publishLink = publishLink;
        setModified();
    }

    public void setResearchAbstract(String researchAbstract) {
        db = new DbUtilities();
        String sql = "UPDATE Research SET researchAbstract = '" + StringUtilities.cleanMySqlInsert(researchAbstract) + "' WHERE researchID = '" + this.researchID + "';";
        try {
            db.executeQuery(sql);
        } catch (Exception ex) {
            ErrorLogger.log("An error has occurred in with the insert query inside of setResearchAbstract. " + ex.getMessage());
            ErrorLogger.log(sql);
        } finally {
            db.closeMySQLConnection();
        }
        this.researchAbstract = StringUtilities.cleanMySqlInsert(researchAbstract);
        setModified();
    }

    private void setModified() {
        this.modified = DATE_FORMAT.format(Calendar.getInstance().getTime());
        db = new DbUtilities();
        String sql = "UPDATE Research SET modified = '" + this.modified + "' WHERE researchID = '" + this.researchID + "';";
        try {
            db.executeQuery(sql);
        } catch (Exception ex) {
            ErrorLogger.log("An error has occurred in with the insert query inside of setModified. " + ex.getMessage());
            ErrorLogger.log(sql);
        } finally {
            db.closeMySQLConnection();
        }
    }

    public String getResearchID() {
        return researchID;
    }

    public String getResearchName() {
        return researchName;
    }

    public String getJournal() {
        return journal;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public String getPublishDate() {
        return publishedDate;
    }

    public String getPublishLink() {
        return publishLink;
    }

    public String getResearchAbstract() {
        return researchAbstract;
    }

    /**
     * Creates a JSON Object representing a Research object
     *
     * @return JSON Object
     */
    public JSONObject getResearchAsJson() {

        JSONObject Research = new JSONObject();

        try {
            Research.put("userID", this.userID);
            Research.put("researchID", this.researchID);
            Research.put("researchName", this.researchName);
            Research.put("journal", this.journal);
            Research.put("submissionDate", this.submissionDate);
            Research.put("publishedDate", this.publishedDate);
            Research.put("researchAbstract", this.researchAbstract);
            Research.put("created", this.created);
            Research.put("modified", this.modified);
            Research.put("publishLink", this.publishLink);
        } catch (JSONException ex) {
            ErrorLogger.log("An error occurred within getResearchAsJSON. " + ex.getMessage());
        }
        return Research;
    }

    /**
     * Sets a Research objects properties given JSON
     *
     * @param Research JSON Object
     */
    public final void setResearchFromJSON(JSONObject Research) {

        try {
            setUserID(Research.getString("userID"));
            setResearchName(Research.getString("researchName"));
            setJournal(Research.getString("journal"));
            setSubmissionDate(Research.getString("submissionDate"));
            setPublishedDate(Research.getString("publishedDate"));
            setResearchAbstract(Research.getString("researchAbstract"));
            setPublishLink(Research.getString("publishLink"));
        } catch (JSONException ex) {
            ErrorLogger.log("An error occurred within setResearchFromJSON. " + ex.getMessage());
        }
    }
}



