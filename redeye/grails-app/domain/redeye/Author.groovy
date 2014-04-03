package redeye

class Author {

    String idString
    String nickName
    String gender
    String location

    Date lastModeratedTime
    Date submissionTime

    static constraints = {
        gender nullable: true
        location nullable: true
    }
}
