package dev.kxxcn.app_with.data.model.diary;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kxxcn on 2018-09-13.
 */
public class Diary implements Parcelable {

	private int id;
	private String writer;
	private String letter;
	private String letterDate;
	private String letterPlace;
	private int fontStyle;
	private int fontColor;
	private float fontSize;
	private int primaryPosition;
	private String galleryName;
	private int galleryBlur;
	private int letterPosition;

	public Diary(int id, String writer, String letter, String letterDate, String letterPlace, int fontStyle, int fontColor, float fontSize, int primaryPosition, String galleryName, int galleryBlur, int letterPosition) {
		this.id = id;
		this.writer = writer;
		this.letter = letter;
		this.letterDate = letterDate;
		this.letterPlace = letterPlace;
		this.fontStyle = fontStyle;
		this.fontColor = fontColor;
		this.fontSize = fontSize;
		this.primaryPosition = primaryPosition;
		this.galleryName = galleryName;
		this.galleryBlur = galleryBlur;
		this.letterPosition = letterPosition;
	}

	public int getId() {
		return id;
	}

	public String getWriter() {
		return writer;
	}

	public String getLetter() {
		return letter;
	}

	public String getLetterDate() {
		return letterDate;
	}

	public String getLetterPlace() {
		return letterPlace;
	}

	public int getFontStyle() {
		return fontStyle;
	}

	public int getFontColor() {
		return fontColor;
	}

	public float getFontSize() {
		return fontSize;
	}

	public int getPrimaryPosition() {
		return primaryPosition;
	}

	public String getGalleryName() {
		return galleryName;
	}

	public int getGalleryBlur() {
		return galleryBlur;
	}

	public int getLetterPosition() {
		return letterPosition;
	}

	public static Creator<Diary> getCREATOR() {
		return CREATOR;
	}

	protected Diary(Parcel in) {
		writer = in.readString();
		letter = in.readString();
		letterDate = in.readString();
		letterPlace = in.readString();
		fontStyle = in.readInt();
		fontColor = in.readInt();
		fontSize = in.readFloat();
		primaryPosition = in.readInt();
		galleryName = in.readString();
		galleryBlur = in.readInt();
		letterPosition = in.readInt();
	}

	public static final Creator<Diary> CREATOR = new Creator<Diary>() {
		@Override
		public Diary createFromParcel(Parcel source) {
			return new Diary(source);
		}

		@Override
		public Diary[] newArray(int size) {
			return new Diary[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(writer);
		dest.writeString(letter);
		dest.writeString(letterDate);
		dest.writeString(letterPlace);
		dest.writeInt(fontStyle);
		dest.writeInt(fontColor);
		dest.writeFloat(fontSize);
		dest.writeInt(primaryPosition);
		dest.writeString(galleryName);
		dest.writeInt(galleryBlur);
		dest.writeInt(letterPosition);
	}

}
