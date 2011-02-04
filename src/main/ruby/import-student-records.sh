if [[ "$#" -eq "0" ]] || [[ "$#" -gt "1" ]]; then
	echo "Aborting: Please enter a filename or type --help"
elif [[ "$1" = "--help" ]]; then
	echo -e "
\tTo import a spreadsheet, please type:
\t"$0" 'filename'
\n\teg. "$0" spreadsheet.xls
	"		
else 
#TO DO: check to make sure the string we pass is findable
ruby RowManager.rb "$1"

fi
