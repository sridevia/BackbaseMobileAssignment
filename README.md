# BackbaseMobileAssignment

Search Cities Solution:
•	Given cities are bucketized into map with first letter of the city name. Maintaining two Collections. These collections are prepared on the app open first time.
	o	List<Cities> with full cities 
	o	Map<Char, List<Cities> with bucketized data. Example : {‘a’  City(‘Alabama’)
•	When user launch the app and no search string given on the search field, List is populated with the List<Cities> with full cities.
•	User starts typing in the search field.
	o	When user types the first letter, will get the data from the Prepared Base Map.
	o	When user types the second letter, search happens on the list prepared in the above step and placing the search result into a temporary map (Key: user search keyword and Value: Result) to reuse the result when user click on backspace key to go clear the text.
	o	When user types the third letter, filter happens on the result of the two letters search and result of 3 letters will put into the temporary map.
	o	Temporary map is cleared when user clears all the text.
	o	Advantage of this temporary map is when user keep typing, we will update this map with search results and when user clears the text letter by letter, we can retrieve the data from the map rather than preparing it again. So that the search result to the user will be faster. 

Note:- Launched the Given About Information Activity when use clicks on the Button/Icon on every row, but there no city name implementation in the Activity. So I don’t see any reason to write the UI test cases for the About Information Activity as there is no dynamic data in it.

