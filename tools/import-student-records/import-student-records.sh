if [[ "$1" = "--help" ]]; then

	echo -e "
    \tTo import a spreadsheet, please type:
    \t"$0" 'excel sheet filepath' 'database properties filepath'
    \n\teg. "$0" spreadsheet.xls database.properties\n"

elif [[ "$#" -ne "2" ]]; then
	echo "Aborting: Please enter a filename and a database properties file or type --help"
else 

    ruby import_students.rb "$1"
    javac WriteToDB.java
    java -classpath "../../lib/main/*:." WriteToDB "../../target/import_students.sql" "$2"

fi
