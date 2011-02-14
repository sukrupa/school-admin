if [[ "$1" = "--help" ]]; then

	echo -e "
    \tTo import a spreadsheet, please type:
    \t"$0" 'excel sheet filepath'
    \n\teg. "$0" spreadsheet.xls\n"

elif [[ "$#" -ne "1" ]]; then
	echo "Aborting: Please enter a filename or type --help"
else 
	
	echo "\n-------------------------------------
	Installing Roo...
	-------------------------------------\n"
	
	sudo gem install roo

	echo "\n-------------------------------------
	Installing Roo Dependencies...
	-------------------------------------\n"
	# ROO DEPENDENCIES #
	sudo gem install rubyzip
	sudo gem install nokogiri
	sudo gem install spreadsheet
	sudo gem install bones
	sudo gem install google-spreadsheet-ruby

	
    ruby import_students.rb "$1"
fi
