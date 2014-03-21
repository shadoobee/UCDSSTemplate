/*
// Andy Richmond
// Letters & Science Deans' Office
// University of California, Davis

// Special thanks to the jQuery multiSelect by
// Cory S.N. LaViska, A Beautiful Site (http://abeautifulsite.net/)
// which provided a base and many ideas for this code

// Dependencies:	jQuery 1.4.2 or higher (http://jquery.com/)
//					jquery ui 1.8.1 or higher (http://jqueryui.com/)
//
// Licensing & Terms of Use
// 
// This plugin is dual-licensed under the GNU General Public License and the MIT License and
// is copyright 2010 UC Regents, Davis campus. 
*/
(function ($) {
	var WIDGET_NAME = "multiselectdropdown";
	var preShowValues;
	$.widget("ui." + WIDGET_NAME, {

		options: {
			width: null,
			showSelectAll: false,
			selectAllText: "Select All",
			noneSelectedText: "Select options",
			selectedItemsText: "% selected",
			optGroupSelectable: false,
			listHeight: 150
		},

		_create: function () {
			var self = this;

			// setup some holder variables
			this._hover = null; // the current hovered item
			this._selectable = []; // any item that can be selected (has a checkbox)
			this._checkboxOptions = []; // any checkbox that has an actual value (mapped to an <select/> <option/>)
			this._checkAll = null;  // the select all checkbox if one exists

			// make sure the original <select/> is set to multiple
			this.element.attr("multiple", "multiple");

			var multiSelect = this.multiSelect = $('<a href="javascript:;" class="ui-multiselectdropdown"><span></span></a>');
			this.element.after(multiSelect);

			var multiSelectOptions = this.multiSelectOptions = $('<div class="ui-multiselect-options"></div>');
			$(document.body).append(multiSelectOptions);

			// Hide the original <select/> element
			this.element.hide();

			// Build the dropdown options
			this._buildOptions();

			// attach events if as long as the multiselect is not disabled
			if (this.options.disabled) {
				multiSelect.addClass("disabled");
			}
			else {
				this._attachEvents();
			}

			preShowValues = this.element.val();
		},

		_attachEvents: function () {
			var self = this, multiSelect = this.multiSelect, multiSelectOptions = this.multiSelectOptions;
			// Events
			multiSelect.click(function () {
				// Show/hide on click
				if ($(this).hasClass('active')) {
					self._hide();
				} else {
					self._show();
				}
				//return false;
			}).focus(function () {
				// So it can be styled with CSS
				multiSelect.addClass('focus');
			}).blur(function () {
				// So it can be styled with CSS
				multiSelect.removeClass('focus');
			}).keydown(function (e) {
				var allOptions = self._selectable;
				// Is dropdown visible?
				if (multiSelectOptions.css("visibility") != "hidden") {
					// Dropdown is visible
					// Tab
					if (e.keyCode == $.ui.keyCode.TAB) {
						self._hide();
						return true;
					}

					// ESC
					if (e.keyCode == $.ui.keyCode.ESCAPE) {
						// Hide dropdown
						multiSelect.addClass("focus").trigger("click");
					}
					// Up, Down, Left, Right
					if (e.keyCode == $.ui.keyCode.UP || e.keyCode == $.ui.keyCode.DOWN || e.keyCode == $.ui.keyCode.LEFT || e.keyCode == $.ui.keyCode.RIGHT) {
						var oldHoverIndex = self._hover ? allOptions.index(self._hover) : -1;
						var newHoverIndex = -1;

						// if there is no current highlighted item then highlight the first item
						if (oldHoverIndex < 0) {
							// Default to first item
							self._setHover(allOptions.first());
						}
						// else if we are moving down and there is a next item then move
						else if ((e.keyCode == $.ui.keyCode.DOWN || e.keyCode == $.ui.keyCode.RIGHT) && oldHoverIndex < allOptions.length - 1) {
							newHoverIndex = oldHoverIndex + 1;
						}
						// else if we are moving up and there is a prev item then move
						else if ((e.keyCode == $.ui.keyCode.UP || e.keyCode == $.ui.keyCode.LEFT) && oldHoverIndex > 0) {
							newHoverIndex = oldHoverIndex - 1;
						}

						if (newHoverIndex >= 0) {
							self._setHover(allOptions.eq(newHoverIndex));

							// Adjust the viewport if necessary
							self._adjustViewPort();
						}

						return false;
					}

					// Enter, Space
					if (e.keyCode == $.ui.keyCode.ENTER || e.keyCode == $.ui.keyCode.SPACE) {
						var checkbox = self._hover.find("input:checkbox");
						checkbox.attr("checked", !checkbox.is(':checked'));

						// if we are dealing with the select all
						if (checkbox.hasClass("ui-multiselect-selectall")) {
							self._selectAll(checkbox);
						}
						// else if we are dealing with the selectable optgroup
						else if (checkbox.hasClass("ui-multiselect-optgroup")) {
							self._selectOptGroup(checkbox);
						}
						// else we must have a regular option
						else {
							self._selectOption(checkbox);
						}

						return false;
					}

					// Any other standard keyboard character (but not when the ctrl or alt is down) - try and match the first character of an option
					if ((e.keyCode >= 33 && e.keyCode <= 126) && !(e.ctrlKey || e.altKey)) {
						// find the next matching item after the current hovered item
						var match = allOptions.filter("label:startsWith(" + String.fromCharCode(e.keyCode) + ")");

						var currentHoverIndex = match.index(self._hover);

						// filter the set to any items after the current hovered item
						var afterHoverMatch = match.filter(function (index) {
							return index > currentHoverIndex;
						});

						// if there were no item after the current hovered item then try using the full search results (filtered to the first one)
						match = (afterHoverMatch.length >= 1 ? afterHoverMatch : match).filter("label:first");

						if (match.length == 1) {
							// if we found a match then move the hover
							self._setHover(match);

							self._adjustViewPort();
						}
					}
					// Dropdown is not visible
				} else {
					// up, down, enter, space - show the dropdown
					if (e.keyCode == $.ui.keyCode.UP || e.keyCode == $.ui.keyCode.DOWN || e.keyCode == $.ui.keyCode.ENTER || e.keyCode == $.ui.keyCode.SPACE) {
						// Show dropdown
						self._show();

						return false;
					}
				}
				// Prevent enter key from submitting form
				if (e.keyCode == $.ui.keyCode.ENTER) return false;
			});
		},

		_detachEvents: function () {
			this.multiSelect.unbind("click").unbind("focus").unbind("blur").unbind("keydown");
		},

		_renderSelectAll: function () {
			var self = this;

			// if we should have a select all option (and it isn't already there) then add it
			if (this.options.showSelectAll && !this._checkAll && this.length() > 0) {
				var checkAllLabel = $('<label class="ui-multiselect-selectall">' + this.options.selectAllText + '</label>');
				this._checkAll = $('<input type="checkbox" class="ui-multiselect-selectall" />').click(function () {
					self._selectAll($(this));
				});
				this.multiSelectOptions.prepend(checkAllLabel.prepend(this._checkAll));
			}
			// else if it exists and shouldn't then remove it
			else if (!this.options.showSelectAll && this._checkAll) {
				this._checkAll.parent().remove();
				this._checkAll = null;
			}
		},

		// render the html for a single option
		_renderOption: function (option) {
			var html = '<label><input type="checkbox" value="' + option.value + '"';
			if (option.selected) {
				html += ' checked="checked"';
			}
			if (option.disabled) {
				html += ' disabled="disabled"';
			}
			html += ' />' + $(option).text() + '</label>';

			return html;
		},

		// render the html for the options/optgroups
		_renderOptions: function (elements) {
			var self = this;
			var html = "";

			elements.each(function () {
				if (this.nodeName.toLowerCase() == "optgroup") {
					html += '<label class="ui-multiselect-optgroup">';

					if (self.options.optGroupSelectable) {
						html += '<input type="checkbox" class="ui-multiselect-optgroup" />';
					}
					html += $(this).attr('label');


					html += '</label><div class="ui-multiselect-optgroup-container">';

					html += self._renderOptions($(this).children("option"));

					html += '</div>';
				}
				else {
					html += self._renderOption(this);
				}
			});

			return html;
		},

		// Building the actual options
		_buildOptions: function () {
			var self = this;
			var multiSelect = this.multiSelect;
			var multiSelectOptions = this.multiSelectOptions;

			// clear any existing options
			multiSelectOptions.html("");
			this._checkAll = null; // reset the select all

			// render the selec all option if needed
			this._renderSelectAll();

			// generate the html for the new options
			multiSelectOptions.append(this._renderOptions(this.element.children("option, optgroup")));

			// set the size of the drop down
			this._setSize();

			this._initBaseCollections();

			// Handle OptGroup oncheck
			if (this.options.optGroupSelectable) {
				multiSelectOptions.addClass("ui-multiselect-optgrouphascheckboxes");

				// add the onclick
				this._selectable.find("input.ui-multiselect-optgroup").click(function (event) {
					self._selectOptGroup($(this));
				});

				// check any optgroups that should be selected (because all their children are selected)
				multiSelectOptions.find("label.ui-multiselect-optgroup").each(function () {
					self._updateOptGroup($(this));
				});
			}

			// Handle all option checkboxes
			this._checkboxOptions.click(function () {
				self._selectOption($(this));
			});

			// Initial display selected (mark any labels as checked where the checkbox is checked)
			multiSelectOptions.find("input:checked").parent().addClass("checked");

			// Initial display disabled (mark any labels as disabled where the checkbox is disabled)
			multiSelectOptions.find("input:disabled").parent().addClass("disabled");

			// Initialize selected and select all 
			this._updateSelected();

			// Handle hovers
			this._selectable.mouseover(function () {
				self._setHover($(this));
			});

			if (multiSelectOptions.find("label").length == 0) {
				multiSelectOptions.append("&nbsp;");
			}
		},

		_initBaseCollections: function () {
			// store a collection of all selectable options
			this._selectable = this.multiSelectOptions.find("label:has(input:not(:disabled))");

			// store a collection of all valued checkboxes (ignore select all and optgroups)
			this._checkboxOptions = this._selectable.not(".ui-multiselect-selectall, .ui-multiselect-optgroup").find("input:not(:disabled)");
		},

		_setSize: function () {
			var multiSelect = this.multiSelect
			, multiSelectOptions = this.multiSelectOptions;

			// set the width of the new dropdown
			// if a width was defined then use it
			if (this.options.width) {
				multiSelect.find("span").css("width", parseInt(this.options.width) + "px");
			}
			// else use the <select/> objects width
			else {
				multiSelect.find("span").css("width", this.element.width() + "px");
			}

			multiSelectOptions.css({ "height": "", "width": "" });

			// variables needed to account for width changes due to a scrollbar
			var initialWidth = multiSelectOptions.width();
			var hasScrollbar = false;

			// set the height of the dropdown options
			if (multiSelectOptions.height() >= this.options.listHeight) {
				multiSelectOptions.css("height", this.options.listHeight + "px");
				hasScrollbar = true;
			} else {
				multiSelectOptions.css("height", "");
			}

			// if the there is a scrollbar and the browser did not already handle adjusting the width (i.e. Firefox) then we will need to manaually add the scrollbar width
			var scrollbarWidth = hasScrollbar && (initialWidth == multiSelectOptions.width()) ? 17 : 0;

			// set the width of the dropdown options
			if ((multiSelectOptions.width() + scrollbarWidth) < multiSelect.outerWidth()) {
				multiSelectOptions.css("width", multiSelect.outerWidth() - 2/*border*/ + "px");
			} else {
				multiSelectOptions.css("width", (multiSelectOptions.width() + scrollbarWidth) + "px");
			}

			// Apply bgiframe if available on IE6
			if ($.fn.bgiframe) multiSelect.next(".ui-multiselect-options").bgiframe({ width: multiSelectOptions.width(), height: multiSelectOptions.height() });
		},

		// Set the hover on an option
		_setHover: function (label) {
			if (this._hover) {
				this._hover.removeClass("hover");
			}
			if (label) {
				this._hover = label.addClass("hover");
			}
		},

		// Handle select all
		_selectAll: function (checkbox) {
			// update all the child checkboxes
			this._selectable.find("input:checkbox").attr("checked", checkbox.is(':checked')).parent("label").toggleClass("checked", checkbox.is(':checked'));

			this._updateSelected();
		},

		// Handle optgroup select
		_selectOptGroup: function (checkbox) {
			// set the label checked class
			checkbox.parent("label").toggleClass("checked", checkbox.is(':checked'));

			// update all the child checkboxes
			checkbox.parent().next(".ui-multiselect-optgroup-container").find("input:checkbox").attr("checked", checkbox.is(':checked')).parent("label").toggleClass("checked", checkbox.is(':checked'));

			this._updateSelected();
		},

		// Handle an option selected
		_selectOption: function (checkbox) {
			// set the label checked class
			checkbox.parent("label").toggleClass("checked", checkbox.is(':checked'));

			// handle any optgroups
			if (checkbox.parent().parent().hasClass("ui-multiselect-optgroup-container")) {
				this._updateOptGroup(checkbox.parent().parent().prev());
			}

			this._updateSelected();
		},

		// mark any checkboxes as selected based on the <select/> selected status
		_mirrorSelected: function () {
			var self = this;
			var values = this.element.val();
			if (values && values.length > 0) {
				this._checkboxOptions.each(function () {
					if ($.inArray($(this).val(), values) >= 0) {
						$(this).attr("checked", true).parent().addClass("checked");

						// handle any optgroups
						if ($(this).parent().parent().hasClass("ui-multiselect-optgroup-container")) {
							self._updateOptGroup($(this).parent().parent().prev());
						}
					}
				});
			}
			this._updateSelected();
		},

		// Adjust the viewport if necessary
		_adjustViewPort: function () {
			var multiSelectOptions = this.multiSelectOptions;

			if (this._hover && this._hover.length) {
				// check for and move down
				var selectionBottom = this._hover.position().top + this._hover.outerHeight();

				if (selectionBottom > multiSelectOptions.innerHeight()) {
					multiSelectOptions.scrollTop(multiSelectOptions.scrollTop() + selectionBottom - multiSelectOptions.innerHeight());
				}

				// check for and move up						
				if (this._hover.position().top < 0) {
					multiSelectOptions.scrollTop(multiSelectOptions.scrollTop() + this._hover.position().top);
				}
			}
		},

		// Update the optgroup checked status
		_updateOptGroup: function (optGroup) {
			// Determine if the optgroup should be checked
			if (this.options.optGroupSelectable) {
				var optGroupOptions = optGroup.next().find("input:checkbox");
				var optGroupSelected = optGroupOptions.length > 0 ? true : false;  // default to true if there are ant sub options
				optGroupOptions.each(function () {
					if (!$(this).is(':checked')) {
						optGroupSelected = false;
						return false;
					}
				});

				optGroup.find("input.ui-multiselect-optgroup").attr("checked", optGroupSelected).parent("label").toggleClass("checked", optGroupSelected);
			}
		},

		// Update the textbox with the total number of selected items, and determine select all
		_updateSelected: function () {	
			var multiSelect = this.multiSelect;
			var multiSelectOptions = this.multiSelectOptions;

			var i = 0;
			var selectAll = true;
			var display = "";
			var selectedValues = [];

			this._checkboxOptions.each(function () {
				if ($(this).is(':checked')) {
					i++;
					display = display + $(this).parent().text() + ", ";
					selectedValues.push($(this).val());
				}
				else selectAll = false;
			});

			// trim any end comma and surounding whitespace
			display = display.replace(/\s*\,\s*$/, '');

			if (i == 0) {
				multiSelect.find("span").html(this.options.noneSelectedText);
			} else {
				if (this.options.selectedItemsText == "*") {
					multiSelect.find("span").html(display);
					multiSelect.attr("title", display);
				} else {
					multiSelect.find("span").html(this.options.selectedItemsText.replace("%", i));
				}
			}

			// Determine if Select All should be checked
			if (this.options.showSelectAll && this._checkAll) {
				this._checkAll.attr("checked", selectAll).parent("label").toggleClass("checked", selectAll);
			}

			// update the source <select/> element
			this.element.val(selectedValues);

			this._trigger("change", null, selectedValues);
		},

		// Hide the dropdown
		_hide: function () {
			var self = this, multiSelect = this.multiSelect, multiSelectOptions = this.multiSelectOptions;

			multiSelect.removeClass("active").removeClass("hover");
			multiSelectOptions.css("visibility", "hidden");

			this._setHover(null);

			// Remove the event listener to the window to close the multiselect if the user clicks off
			$(document).unbind("click.multiselectdropdown", this._hideOnClickOff);

			multiSelectOptions.unbind("click");

			// if the values have changed then call the onchange on the original <select>
			if (!arrayCompare(preShowValues, this.element.val())) {
				this.element.change();
			}
		},

		// Show the dropdown
		_show: function () {
			var self = this, multiSelect = this.multiSelect, multiSelectOptions = this.multiSelectOptions, allOptions = this._selectable;

			// mark as active
			multiSelect.addClass("active");

			// Position it
			var offset = multiSelect.offset();
			multiSelectOptions.css({ top: offset.top + multiSelect.outerHeight() + "px", left: offset.left + "px" });

			// set the first hovered item
			var firstItem = allOptions.filter(".checked").first();
			if (firstItem.length == 0) {
				firstItem = allOptions.first();
			}
			this._setHover(firstItem);

			// adjust the viewport if nessasary
			this._adjustViewPort();

			// Add an event listener to move focus to the multiselct whenever anywhere on the dropdown is clicked
			multiSelectOptions.click(function () {
				multiSelect.focus();
			});

			this._hideOnClickOff = function (event) {
				var foundParent = false;

				$(event.target).parents().andSelf().each(function () {
					if (this == multiSelect[0] || this == multiSelectOptions[0]) {
						foundParent = true;
						return true;
					}
				});

				// If somewhere outside of the multiselect was clicked then hide the multiselect
				if (!foundParent) {
					self._hide();
				}
			}

			// Add an event listener to the window to close the multiselect if the user clicks off
			$(document).bind('click.multiselectdropdown', this._hideOnClickOff);

			preShowValues = this.element.val();

			// show the dropdown
			multiSelectOptions.css("visibility", "visible");
			multiSelect.focus();
		},

		_toggleDisabled: function () {
			var multiSelect = this.multiSelect;

			// if the setting is not disabled but the multiselect is disabled then enable it
			if (!this.options.disabled && multiSelect.hasClass("disabled")) {
				this._attachEvents();
				this.multiSelect.removeClass("disabled");
			}
			// if the setting is disabled but the multiselect is not disabled then disable it
			else if (this.options.disabled && !this.multiSelect.hasClass("disabled")) {
				this._detachEvents();
				this.multiSelect.addClass("disabled");
			}
		},

		_setOption: function (key, value) {
			$.widget.prototype._setData.apply(this, arguments);

			switch (key) {
				case "width":
					this._setSize();
					break;
				case "showSelectAll":
					this._renderSelectAll();
					break;
				case "selectAllText":
				case "noneSelectedText":
				case "selectedItemsText":
					this._updateSelected();
					break;
				case "optGroupSelectable":
					this.rerender();
					break;
				case "listHeight":
					this._setSize();
					break;
				case 'disabled':
					this._toggleDisabled();
			}
		},

		_htmlOptionsFromJson: function (options) {
			var html = '';

			// add in the new options
			for (var i = 0; i < options.length; i++) {
				if (options[i].optgroup) {
					html += '<optgroup label="' + options[i].optgroup + '">';

					html += this._htmlOptionsFromJson(options[i].options);

					html += '</optgroup>';
				}
				else {
					html += '<option value="' + options[i].value + '"';
					if (options[i].selected) {
						html += ' selected="selected"';
					}
					if (options[i].disabled) {
						html += ' disabled="disabled"';
					}
					html += '>' + options[i].text + '</option>';
				}
			}

			return html;
		},

		// function to take in a json array of options and update the <select/> and multiselect interface
		updateOptions: function (options) {
			// clear any existing options
			this.element.html(this._htmlOptionsFromJson(options));

			this.rerender();
		},

		// rerender the multiselect drop down interface to match and changes to the original <select/>
		rerender: function () {
			this._buildOptions();
		},

		disableOptions: function (values) {
			var self = this;

			if (typeof values == "string") {
				values = [values];
			}

			if ($.isArray(values)) {
				this.multiSelectOptions.find("input:checkbox").filter(function () {
					return ($.inArray($(this).val(), values) >= 0);
				}).each(function () {
					$(this).attr("disabled", "disabled").parent().addClass("disabled");
					self.element.find("option[value=" + values + "]").attr("disabled", "disabled");
				}).unbind("click").parent().unbind("mouseover");

				this._initBaseCollections();
			}

		},

		enableOptions: function (values) {
			var self = this;

			if (typeof values == "string") {
				values = [values];
			}
			if ($.isArray(values)) {
				this.multiSelectOptions.find("input:checkbox").filter(function () {
					return ($.inArray($(this).val(), values) >= 0);
				}).each(function () {
					$(this).removeAttr("disabled").parent().removeClass("disabled");
					self.element.find("option[value=" + values + "]").removeAttr("disabled");
				}).click(function () {
					self._selectOption($(this));
				}).parent().mouseover(function () {
					self._setHover($(this));
				});

				this._initBaseCollections();
			}
		},

		// hide one or many options
		hideOptions: function (values) {
			var self = this, selection = $([]);

			// if values were passed in then show those values
			if (arguments.length) {
				if (typeof values == "string") {
					values = [values];
				}
				if ($.isArray(values)) {
					selection = this.multiSelectOptions.find("input:checkbox:visible").filter(function () {
						return ($.inArray($(this).val(), values) >= 0);
					});
				}
			}
			// else no values were sent in so show all
			else {
				selection = this.multiSelectOptions.find("input:checkbox:visible");
			}

			selection.parent().hide();
		},

		// show one or many options
		showOptions: function (values) {
			var self = this, selection = $([]);

			// if values were passed in then hide those values
			if (arguments.length) {
				if (typeof values == "string") {
					values = [values];
				}
				if ($.isArray(values)) {
					selection = this.multiSelectOptions.find("input:checkbox:hidden").filter(function () {
						return ($.inArray($(this).val(), values) >= 0);
					});
				}
			}
			// else no values were sent in so hide all
			else {
				selection = this.multiSelectOptions.find("input:checkbox:hidden");
			}

			selection.parent().show();
		},

		// select(or unselect) values without affecting other values (can pass in a simple value and bool, or an object collection of value bool pairs)
		selected: function (value, selected) {
			// if the user passed in a simple value boolean par then convert it to an object
			if (arguments.length == 2 && typeof (value) == 'string' && typeof (selected) == 'boolean') {
				var obj = {};
				obj[value] = selected;
				value = obj;
			}

			if (arguments.length && typeof (value) == 'object') {
				for (var val in value) {
					this.element.find("option[value=" + val + "]").each(function () {
						this.selected = value[val];
					});
				}

				// mirror the updated original <select/> into the new multiselect interface
				this._mirrorSelected();
			}
		},

		// set or get selected values
		values: function (newValue) {
			// if we are assigning a value
			if (arguments.length) {
				var values = this.element.val(newValue);

				// mirror the updated original <select/> into the new multiselect interface
				this._mirrorSelected();

				return values;
			}
			else {
				return this.element.val();
			}
		},

		length: function () {
			return this.element[0].options.length;
		},

		destroy: function () {
			$.widget.prototype.destroy.apply(this, arguments); // default destroy
			// now do other stuff particular to this widget
			this.multiSelect.remove();
			this.multiSelectOptions.remove();

			this.element.show();
		}
	});

	var defaultPluginMethod = $.fn[WIDGET_NAME];

	// inject a preInit function before the default widget constructor to only allow <select/> tags 
	$.fn[WIDGET_NAME] = function (options) {
		var self = this;

		// only allow <select/> tags to be extended for a multiselect
		if (this.length > 0 && !this[0].nodeName.toLowerCase() == "select") {
			return this;
		}

		return defaultPluginMethod.apply(self, arguments);
	};

	// add a new ":startsWith" search filter
	$.expr[":"].startsWith = function (element, index, tokens) {
		if (!tokens[3]) return false;
		return new RegExp("^\\s*" + tokens[3], "i").test($(element).text());
	};

	arrayCompare = function (a1, a2) {
		if (a1 == null || a2 == null || a1.length != a2.length) { return false; }
		var a = a1.sort(),
        b = a2.sort();
		for (var i = 0; a2[i]; i++) {
			if (a[i] !== b[i]) {
				return false;
			}
		}
		return true;
	};

})(jQuery);