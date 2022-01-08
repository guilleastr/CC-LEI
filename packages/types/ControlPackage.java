package packages.types;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import file.CustomFile;
import file.DirectoryManagerSingleton;
import packages.PackageBuilder;

public class ControlPackage extends Base_Package implements Package_Executor {

    // Usa-se o @ como delimitador

    private short size;
    private byte[] files;

    public ControlPackage(short type, short size, byte[] files) {
        super(type);
        this.size = size;
        this.files = files;
    }

    public List<byte[]> execute() {


        System.out.println("CONTROL: FILES" + DirectoryManagerSingleton.getInstance().parseCustomFiles(new String(files)).toString());


        List<CustomFile> remoteFiles = DirectoryManagerSingleton.getInstance().parseCustomFiles(new String(files));


        List<CustomFile> localFiles = DirectoryManagerSingleton.getInstance()
                .generateCustomFiles(DirectoryManagerSingleton.getInstance().getAvailableFiles());

        List<byte[]> responses = new ArrayList<>();

        try {
		
            for (CustomFile cf : remoteFiles) {
			
                if (!contains(localFiles,cf)) {
			
                    responses.add(PackageBuilder.buildReadPacakge(cf.getName()));
                    continue;
                }
		System.out.println("Checking for local of remote");
                for (CustomFile cf2 : localFiles) {
			
                    if (!contains(remoteFiles,cf2)) {
			System.out.println(cf2.toString());
                        responses.add(PackageBuilder.buildWritePackage(cf2.getName()));
                        continue;
                    }
                    if (cf2.getName().equals(cf.getName())) {
                        if (!cf2.getMd5().equals(cf.getMd5())) {

                            if (cf.getTime() < cf2.getTime()) {
                                responses.add(PackageBuilder.buildWritePackage(cf.getName()));
                            } else {
                                responses.add(PackageBuilder.buildReadPacakge(cf2.getName()));
                            }

                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (responses.size() == 0) {
            try {
                responses.add(PackageBuilder.buildAcknowledgementPackage(0, PackageBuilder.CONTROL_TYPE, ""));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        return responses;
    }

    public short getSize() {
        return size;
    }

    public void setSize(short size) {
        this.size = size;
    }

    public byte[] getfiles() {
        return files;
    }

    public void setfiles(byte[] files) {
        this.files = files;
    }

    /*
     * public String getStringOfFileNames() { StringBuilder sb = new
     * StringBuilder();
     *
     * for (int i = 0; i < files.size(); i++) { sb.append('@');
     * sb.append(files.get(i)); } return sb.toString(); }
     */

    /**
     * @return int return the type
     */
    public int getType() {
        return super.type;
    }

	private boolean contains(List<CustomFile> localFiles, CustomFile cf) {

        for (CustomFile file : localFiles) {

            if (cf.getName() == file.getName())
                return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ControlPackage that = (ControlPackage) o;
        return size == that.size && Objects.equals(files, that.files);
    }

    @Override
    public String toString() {
        return "ControlPackage{" + "type=" + type + ", size=" + size + ", files=" + files + '}';
    }
}
