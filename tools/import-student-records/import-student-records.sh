if [[ "$1" = "--help" ]]; then

	echo -e "
    \tTo import a spreadsheet, please type:
    \t"$0" 'excel sheet filepath'
    \n\teg. "$0" spreadsheet.xls\n"

elif [[ "$#" -ne "1" ]]; then
	echo "Aborting: Please enter a filename or type --help"
else 
    ruby import_students.rb "$1"
fi
