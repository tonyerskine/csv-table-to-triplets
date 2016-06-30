package triplets

class convert {

    static final int indexOfFirstField = 2
    static final int indexOfId = 0
    static final String inputFileName = /C:\Users\daddy\Google Drive\True Dimension\Test Data\Training Data\Test Data - 2000-2009.csv/

    static main(args) {
        def lines = readLines()
        def subjects = readFields(lines)
        def outputFile = new File("output.csv")
        outputFile = printHeaderRow(outputFile)
        processLines(lines, subjects, outputFile)
    }

    private static List readLines() {
        def inputFile = new File(inputFileName)
        def lines = inputFile.readLines()
        println "Input file has ${lines.size()} lines"
        return lines
    }

    private static List readFields(List lines) {
        def fields = lines.first().split(",")[indexOfFirstField..-1]
        println "first fields == ${fields[0..2]}"
        println "last fields == ${fields[-3..-1]}"
        return fields
    }

    private static File printHeaderRow(File outputFile) {
        outputFile << "id,field,value\n"
        return outputFile
    }

    private static processLines(List lines, List subjects, File outputFile) {
        lines[1..-1].each { line ->
            def fieldValues = line.split(",")
            def id = fieldValues[indexOfId]
            println "processing $id"
            processLine(id, subjects, fieldValues, outputFile)
        }
    }

    static def processLine(id, subjects, fields, outputFile) {
        fields[indexOfFirstField..-1].eachWithIndex { grade, i ->
            if (grade) {
                //                println "$id,${subjects[i]},$grade"
                outputFile << "$id,${subjects[i]},$grade\n"
            }
        }
    }
}
