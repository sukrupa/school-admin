if [[ "$1" = "--help" ]]; then

	echo -e "
    \tTo import a spreadsheet, please type:
    \t"$0" 'excel sheet filepath'
    \n\teg. "$0" spreadsheet.xls\n"

elif [[ "$#" -ne "1" ]]; then
	echo "Aborting: Please enter a filename or type --help"
else 
	
	echo 
	"-------------------------------------
	Installing Roo...
	-------------------------------------\n"
	gem install roo

	"-------------------------------------
	Installing Roo Dependencies...
	-------------------------------------\n"
	# ROO DEPENDENCIES #
	gem install rubyzip
	gem install nokogiri
	gem install spreadsheet
	gem install google_spreadsheet
	gem install gimite-google-spreadsheet-ruby
	gem install bones
	gem install google-spreadsheet-ruby

	
    ruby import_students.rb "$1"
fi
