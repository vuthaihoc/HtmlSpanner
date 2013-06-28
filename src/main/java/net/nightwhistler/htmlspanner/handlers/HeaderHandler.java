/*
 * Copyright (C) 2011 Alex Kuiper <http://www.nightwhistler.net>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.nightwhistler.htmlspanner.handlers;

import android.util.Log;
import net.nightwhistler.htmlspanner.SpanStack;

import net.nightwhistler.htmlspanner.style.Style;
import org.htmlcleaner.TagNode;

import android.text.SpannableStringBuilder;

/**
 * Handles Headers, by assigning a relative text-size.
 * 
 * Note that which header is handled (h1, h2, etc) is determined by the tag this
 * handler is registered for.
 * 
 * Example:
 * 
 * spanner.registerHandler("h1", new HeaderHandler(1.5f));
 * spanner.registerHandler("h2", new HeaderHandler(1.4f));
 * 
 * @author Alex Kuiper
 * 
 */
public class HeaderHandler extends StyledTextHandler {

	private final float size;
    private final float bottomMargin;

	/**
	 * Creates a HeaderHandler which gives
	 * 
	 * @param size
	 */
	public HeaderHandler(float size, float bottomMargin) {
        this.size = size;
        this.bottomMargin = bottomMargin;
	}

    @Override
    public void beforeChildren(TagNode node, SpannableStringBuilder builder, SpanStack spanStack) {

        Log.d("HeaderHandler", "Applying style " + getStyle() );

        Style style = getStyle();

        if (style.getDisplayStyle() == Style.DisplayStyle.BLOCK &&
                builder.length() > 0
                && builder.charAt(builder.length() - 1) != '\n') {
            builder.append("\n");

            Log.d("HeaderHandler", "Adding newline");
        } else {
            Log.d("HeaderHandler", "NOT adding newline");
        }
    }

    @Override
    public Style getStyle() {
        return super.getStyle().setRelativeFontSize(size)
                .setFontWeight(Style.FontWeight.BOLD)
                .setDisplayStyle(Style.DisplayStyle.BLOCK)
                .setRelativeMarginBottom(bottomMargin);
    }

}
