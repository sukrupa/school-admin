if [[ "$#" -eq "0" ]] || [[ "$#" -gt "1" ]]; then
	echo "Aborting: Please enter a filename or type --help"
elif [[ "$1" = "--help" ]]; then
	echo -e "
\tTo import a spreadsheet, please type:
\t"$0" 'filename'
\n\teg. "$0" spreadsheet.xls
	"		
else 

ruby import_students.rb "$1"
javac WriteToDB.java
java WriteToDB "import_students.sql"

fi
