// Copyright 2021 Goldman Sachs
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.finos.legend.engine.external.format.protobuf;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.finos.legend.engine.external.shared.format.generations.GenerationOutput;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FromPureToProtoGeneratorTest
{
    private final FromPureToProtoGenerator fromPureToProtoGenerator = new FromPureToProtoGenerator();

    @Test
    public void generateFromFileToPure() throws IOException
    {
        List<GenerationOutput> generationOutputs = fromPureToProtoGenerator.generate(
                getContentFromFile("src/test/resources/fromPureFileToProto/customer.pure"));
        assertThat(generationOutputs.size(), is(1));
        assertStringsAreEqual(generationOutputs.get(0).content, getContentFromFile("src/test/resources/fromPureFileToProto/customer.proto"));
    }

    private String getContentFromFile(String file) throws IOException
    {
        return FileUtils.readFileToString(FileUtils.getFile(file), Charset.defaultCharset());
    }

    private void assertStringsAreEqual(String s1, String s2) {
        assertThat(simplifiedString(s1), is(simplifiedString(s2)));
    }

    private String simplifiedString(String string) {
        return StringUtils.deleteWhitespace(string)
                .replace("\n", "")
                .replace("\r", "");
    }
}
